const projectName = 'onlinerpg';
let currentRoom = getCookie('currentRoom');

showLocation(currentRoom);
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

async function showLocation(location) {
    let newLocation = await getLocation(location);

    let roomImage = document.querySelector('#roomImage');
    roomImage.src = newLocation.srcImage;

    let locationsHtml = generateLocationBarHtml(newLocation);

    fillLocationBar(locationsHtml);
}

async function getLocation(location) {
    const response = await fetch(`/${projectName}/location?location=${location}`);

    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
    }

    return await response.json();
}

function generateLocationBarHtml(location) {
    let locationsHtml = `<h5 class="card-title" id="roomName">${location.name}</h5>
    <p class="card-text">${location.text}</p>`

    location.locations.forEach(
        function (currentValue) {
            locationsHtml = locationsHtml + `<a href="#" class="btn btn-dark" onclick="showLocation('${currentValue}')">${currentValue}</a>\n`
        }
    )

    return locationsHtml;
}
function fillLocationBar(locationsHtml){
    document.getElementById('locationBar').innerHTML = locationsHtml;
}

function getCookie(name) {
    let matches = document.cookie.match(new RegExp("(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}


