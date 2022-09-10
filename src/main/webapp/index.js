const projectName = 'onlinerpg';

showStatistic();

async function showStatistic() {

    let statistic = await getStatistic();

    document.getElementById("username").innerHTML = statistic.username;
    document.getElementById("hpStatus").innerHTML = 'HP: ' + statistic.hp;
}

async function getStatistic() {
    const response = await fetch(`/${projectName}/statistic`);

    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
    }

    return  await response.json();
}





