const projectName = 'onlinerpg';

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





