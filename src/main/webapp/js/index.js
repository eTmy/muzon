const projectName = 'onlinerpg';
let currentRoom = getCookie('currentRoom');

showContent(currentRoom);
showStatistic();

async function showStatistic() {
    let statistic = await getStatistic();

    document.getElementById("login").innerHTML = statistic.login + ': ';
    document.getElementById("hpStatus").innerHTML = 'HP: ' + statistic.hp;
    document.getElementById("strengthStatus").innerHTML = 'STR: ' + statistic.strength;
    document.getElementById("agilityStatus").innerHTML = 'AGI: ' + statistic.agility;
    document.getElementById("staminaStatus").innerHTML = 'CON: ' + statistic.stamina;
    document.getElementById("intellectStatus").innerHTML = 'INT: ' + statistic.intellect;
}

async function getStatistic() {
    const response = await fetch(`/${projectName}/statistic`);

    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
    }

    return await response.json();
}

async function showContent(location) {
    let newLocation = await getLocation(location);

    let roomImage = document.querySelector('#roomImage');
    roomImage.src = newLocation.srcImage;

    console.log('cur loc: ' + roomImage.src);
    console.log('new loc: ' + newLocation.srcImage);

    document.getElementById('npcBar').innerHTML = generateNpcBarHtml(newLocation.creatures);
    document.getElementById('locationBar').innerHTML = generateLocationBarHtml(newLocation);
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
                        <button type="button" class="btn btn-dark">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chat" viewBox="0 0 16 16">
                                <path d="M2.678 11.894a1 1 0 0 1 .287.801 10.97 10.97 0 0 1-.398 2c1.395-.323 2.247-.697 2.634-.893a1 1 0 0 1 .71-.074A8.06 8.06 0 0 0 8 14c3.996 0 7-2.807 7-6 0-3.192-3.004-6-7-6S1 4.808 1 8c0 1.468.617 2.83 1.678 3.894zm-.493 3.905a21.682 21.682 0 0 1-.713.129c-.2.032-.352-.176-.273-.362a9.68 9.68 0 0 0 .244-.637l.003-.01c.248-.72.45-1.548.524-2.319C.743 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7-3.582 7-8 7a9.06 9.06 0 0 1-2.347-.306c-.52.263-1.639.742-3.468 1.105z"></path>
                            </svg>
                        <small>Speak</small>
                        </button>
                        <button type="button" class="btn btn-dark">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-emoji-dizzy" viewBox="0 0 16 16">
                                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"></path>
                                <path d="M9.146 5.146a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 0 1 .708.708l-.647.646.647.646a.5.5 0 0 1-.708.708l-.646-.647-.646.647a.5.5 0 1 1-.708-.708l.647-.646-.647-.646a.5.5 0 0 1 0-.708zm-5 0a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 1 1 .708.708l-.647.646.647.646a.5.5 0 1 1-.708.708L5.5 7.207l-.646.647a.5.5 0 1 1-.708-.708l.647-.646-.647-.646a.5.5 0 0 1 0-.708zM10 11a2 2 0 1 1-4 0 2 2 0 0 1 4 0z"></path>
                            </svg>
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
            locationsHtml = locationsHtml + `<a href="#" class="btn btn-dark" onclick="showContent('${currentValue}')">${currentValue}</a>\n`
        }
    )

    return locationsHtml;
}

function getCookie(name) {
    let matches = document.cookie.match(new RegExp("(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}


