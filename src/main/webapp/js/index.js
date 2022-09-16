const projectName = 'onlinerpg';
let currentRoom = getCookie('currentRoom');
let locationItems = new Map();

refreshPageData(currentRoom);

async function showStatistic() {
    let statistic = await getStatistic();

    document.getElementById("login").innerHTML = 'You: ' + statistic.login;
    document.getElementById("hpStatus").innerHTML = 'HP: ' + statistic.hp;
    document.getElementById("strengthStatus").innerHTML = 'STR: ' + statistic.strength;
    document.getElementById("agilityStatus").innerHTML = 'AGI: ' + statistic.agility;
    document.getElementById("staminaStatus").innerHTML = 'CON: ' + statistic.stamina;
    document.getElementById("intellectStatus").innerHTML = 'INT: ' + statistic.intellect;
    document.getElementById("damageStatus").innerHTML = 'DAMAGE: ' + statistic.damage;
    document.getElementById("armorStatus").innerHTML = 'ARMOR: ' + statistic.armor;
}

async function getStatistic() {
    const response = await fetch(`/${projectName}/statistic`);

    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
    }

    return await response.json();
}

async function refreshPageData(location) {
    let newLocation = await getLocation(location);

    let roomImage = document.querySelector('#roomImage');
    roomImage.src = newLocation.srcImage;

    document.getElementById('npcBar').innerHTML = generateNpcBarHtml(newLocation.creatures);
    document.getElementById('locationBar').innerHTML = generateLocationBarHtml(newLocation);
    document.getElementById('itemBar').innerHTML = generateItemBarHtml(newLocation.items);

    showStatistic();
}

async function getLocation(location) {
    const response = await fetch(`/${projectName}/location?location=${location}`);

    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
    }

    return await response.json();
}

function generateNpcBarHtml(creatures) {
    let npcBarHtml = '';

    creatures.forEach(
        function (creature) {
            npcBarHtml = npcBarHtml + `
            <a href = "#" class = "list-group-item list-group-item-action py-3 lh-sm">
                <div class = "d-flex w-100 align-items-center justify-content-between">
                    <strong class = "mb-1"> ${creature.name}</strong>
                    <strong class = "mb-1">${creature.nickname}</strong>
                </div>
                <img src="${creature.srcImage}" class="card-img-top" id="npcImage" style="margin-bottom: 15px">
                <div class="text-center" style="margin-bottom: 15px">
                    <strong class="mb-1">
                        HP: ${creature.hp}
                        STR: ${creature.strength}
                        AGI: ${creature.agility}
                        CON: ${creature.stamina}
                        INT: ${creature.intellect}
                    </strong>
                </div>
                <div class="text-center">
                    <div class="mb-1">${creature.description}</div>
                    <div style="margin-top: 15px"> 
                        <button type="button" class="btn btn-dark" onclick="showDialog('${creature.name}', 1, '${creature.srcImage}')">
                            <small>Speak</small>
                        </button>
                        <button type="button" class="btn btn-dark">
                            <small>Attack</small>
                        </button>
                    </div>
                </div>
        </a>`
        }
    )

    return npcBarHtml;
}

function generateLocationBarHtml(location) {
    let locationsHtml = `<h5 class="card-title" id="roomName">${location.name}</h5>
    <p class="card-text">${location.text}</p>`

    location.locations.forEach(
        function (currentValue) {
            locationsHtml = locationsHtml + `<a href="#" class="btn btn-dark" onclick="refreshPageData('${currentValue}')">${currentValue}</a>\n`
        }
    )

    return locationsHtml;
}

function generateItemBarHtml(items){
    let itemBarHtml = '';

    items.forEach(
        function (item) {
            locationItems.set(item.name, item);
            itemBarHtml = itemBarHtml + `
            <a href = "#" class = "list-group-item list-group-item-action py-3 lh-sm">
                <div class = "d-flex w-100 align-items-center justify-content-between">
                    <strong class = "mb-1"> ${item.name}</strong>
                    <strong class = "mb-1">${item.type}</strong>
                </div>
                <img src="${item.srcImage}" class="card-img-top" id="npcImage" style="margin-bottom: 15px">
                <div class="text-center">
                    <div class="mb-1">${item.description}</div>
                    <div style="margin-top: 15px"> 
                        <button type="button" class="btn btn-dark" onclick="takeItem('${item.name}')"> 
                            <small>Take</small>
                        </button>
                    </div>
                </div>
        </a>`
        }
    )

    //document.cookie =encodeURIComponent('locationItems') + '=' + encodeURIComponent(locationItems);
    return itemBarHtml;
}

function getCookie(name) {
    let matches = document.cookie.match(new RegExp("(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}

async function showDialog(npcName, messageId, srcImage, questId) {
    //TODO сделать обработку сетки диалогов со стороны фронта вместо бэкенда
    if (questId !== undefined && questId !== '0') {
        acceptQuest(npcName, questId);
        return;
    }

    if (messageId === '0') {
        return;
    }

    let dialog = await getDialog(npcName, messageId);

    showModal(getDialogHtml(npcName, dialog, srcImage));
}

async function getDialog(npcName, messageId) {
    const response = await fetch(`/${projectName}/dialog?npcName=${npcName}&messageId=${messageId}`);

    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
    }

    return await response.json();
}

function getDialogHtml(npcName, dialog, srcImage) {
    let dialogHtml = `
        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabIndex="-1"
             aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="align-items-center bg-dark text-white p-2" style="text-align: center"> 
                        <span class="fs-5 fw-semibold text-white">
                            <strong>${npcName}</strong>
                        </span>                                             
                    </div>
                    
                    <div class="list-group-item list-group-item-action py-3 lh-sm text-center">
                        <img src="${srcImage}" class="card-img-top">                                              
                    </div>
                    <div class="text-center" style="margin: 15px">
                        ${dialog.text}
                    </div>
                    <div class="d-grid gap-2">
        `;
    dialog.answers.forEach(
        function (answer) {
            dialogHtml = dialogHtml + `
                <button type="button" class="btn btn-dark"  style="margin: 1px" data-bs-dismiss="modal" onclick="showDialog('${npcName}', '${answer.nextMessageId}', '${srcImage}','${answer.questId}')">${answer.text}</button>       
            `;
        }
    )

    dialogHtml = dialogHtml + `
                    </div>
                </div>
            </div>
        </div>`

    return dialogHtml;
}

async function acceptQuest(npcName, questId) {
    const response = await fetch(`/${projectName}/quest?npcName=${npcName}&questId=${questId}&isAccepted=true`);

    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
    }
}

async function finishQuest(npcName, questId) {
    const response = await fetch(`/${projectName}/quest?npcName=${npcName}&questId=${questId}&isFinished=true`);

    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
    }
}

let modalWrap = null;
const showModal = (dialogHtml) => {

    if (modalWrap !== null) {
        modalWrap.remove();
    }

    modalWrap = document.createElement('div');
    modalWrap.innerHTML = dialogHtml;
    document.body.append(modalWrap);

    let modal = new bootstrap.Modal(modalWrap.querySelector('.modal'));
    modal.show();
}

async function showQuests() {
    let quests = await getQuests();

    showModal(getQuestsHtml(quests));
}

async function getQuests() {
    const response = await fetch(`/${projectName}/questlist`);

    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
    }

    return await response.json();
}

function getQuestsHtml(quests){
    //TODO Отрефакторить этот ужас
    let questHtml = `
        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabIndex="-1"
             aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="align-items-center bg-dark text-white p-2" style="text-align: center"> 
                        <span class="fs-5 fw-semibold text-white">
                            <strong>Quests</strong>
                        </span>                                             
                    </div>                    
                    <div class="list-group-item list-group-item-action py-3 lh-sm text-left">
                        <div class="d-grid gap-2">
                            <strong>Active quests:</strong>
                            <ul class="nav col-12 col-md-auto mb-2 align-items-center justify-content-center mb-md-0" id="activeQuests">
        `;

    quests.forEach(
        function (quest) {
            if (!quest.finished) {
                questHtml = questHtml + `
                    <li><a class="nav-link px-2 link-dark">${quest.name} : </a></li>
                    <li><a class="nav-link px-2 link-dark">${quest.description}</a></li>
            `;
            }
        }
    )

    questHtml = questHtml + `
                        </ul>                       
                        <strong>Finished quests:</strong>
                        <ul class="nav col-12 col-md-auto mb-2 align-items-center justify-content-center mb-md-0" id="finishedQuests">
        `;

    quests.forEach(
        function (quest) {
            if (quest.finished) {
                questHtml = questHtml + `
                    <li><a class="nav-link px-2 link-dark">${quest.name}</a></li>
                    <li><a class="nav-link px-2 link-dark">${quest.description}</a></li>
            `;
            }
        }
    )

    questHtml = questHtml + `
                            </ul>
                            <button type="button" class="btn btn-dark"  style="margin: 1px" data-bs-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>`

    return questHtml;
}

async function takeItem(itemName) {
    let item = locationItems.get(itemName);

    const data = {
        item: item,
        action: 'take'
    }

    const response = await fetch(`/${projectName}/item`, {
        method: 'POST',
        redirect: 'follow',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json'
        }
    });

    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
    }

    refreshPageData(currentRoom);
}