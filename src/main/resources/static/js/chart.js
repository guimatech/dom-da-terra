var dataPieChartInternTask = [ document.getElementById("internalTasksOnTime").value,
                               document.getElementById("internalTasksOutOfTime").value,
                               document.getElementById("stagnantInternalTasks").value ];

var dataPieChartExternalTask = [ document.getElementById("externalTasksOnTime").value,
                                 document.getElementById("externalTasksOutOfTime").value,
                                 document.getElementById("stagnantExternalTasks").value ];

var dataPieChartGeneralTasks = [ document.getElementById("tasksDone").value,
                                 document.getElementById("tasksInProgress").value ];

var dataPieChartPendingTasks = [ document.getElementById("tasksWithoutDueDate").value,
                                 document.getElementById("tasksWithoutPreviewDate").value,
                                 document.getElementById("tasksWithoutResponsible").value,
                                 document.getElementById("tasksWithoutStatus").value,
                                 document.getElementById("tasksWithoutDescription").value,
                                 document.getElementById("tasksWithoutNID").value];

var ctxPieChartInternTask = document.getElementById("pieChartInternTask");
var pieChartInternTask = new Chart(ctxPieChartInternTask, {
    type: 'pie',
    options: {
        legend: {
            position: 'left',
            labels: {
                boxWidth: 14,
                fontStyle: 'italic',
                fontColor: '#aaa',
                usePointStyle: true,
                fontSize: 14,
            }
        },
    },
    data: {
        labels: [
            "No prazo",
            "Fora do prazo",
            "Estagnadas"
        ],
        datasets: [
            {
                data: dataPieChartInternTask,
                borderWidth: 10,
                backgroundColor: [
                    '#4bc0c0',
                    '#ffcd56',
                    '#ff6384',
                ],
                hoverBackgroundColor: [
                    '#00e2e2',
                    '#ffc53b',
                    '#ff486f',
                ]
            }]
        }
    });


var ctxPieChartExternalTask = document.getElementById("pieChartExternalTask");
var pieChartExternalTask = new Chart(ctxPieChartExternalTask, {
    type: 'pie',
    options: {
        legend: {
            position: 'left',
            labels: {
                boxWidth: 14,
                fontStyle: 'italic',
                fontColor: '#aaa',
                usePointStyle: true,
                fontSize: 14,
            }
        },
    },
    data: {
        labels: [
            "No prazo",
            "Fora do prazo",
            "Estagnadas"
        ],
        datasets: [
            {
                data: dataPieChartExternalTask,
                borderWidth: 10,
                backgroundColor: [
                    '#4bc0c0',
                    '#ffcd56',
                    '#ff6384',
                ],
                hoverBackgroundColor: [
                    '#00e2e2',
                    '#ffc53b',
                    '#ff486f',
                ]
            }]
        }
    });


var ctxPieChartGeneralTasks = document.getElementById("pieChartGeneralTasks");
var pieChartExternalTask = new Chart(ctxPieChartGeneralTasks, {
    type: 'pie',
    options: {
        legend: {
            position: 'left',
            labels: {
                boxWidth: 14,
                fontStyle: 'italic',
                fontColor: '#aaa',
                usePointStyle: true,
                fontSize: 14,
            }
        },
    },
    data: {
        labels: [
            "Concluídas",
            "Em aberto",
        ],
        datasets: [
            {
                data: dataPieChartGeneralTasks,
                borderWidth: 10,
                backgroundColor: [
                    '#4bc0c0',
                    '#ffcd56',
                ],
                hoverBackgroundColor: [
                    '#00e2e2',
                    '#ffc53b',
                ]
            }]
        }
    });


var ctxPieChartPendingTasks = document.getElementById("pieChartPendingTasks");
var pieChartExternalTask = new Chart(ctxPieChartPendingTasks, {
    type: 'horizontalBar',
    options: {
        legend: {
            display: false,
        },
    },
    data: {
        labels: [
            "Atividades sem data de entrega",
            "Atividades sem data de previsão",
            "Atividades sem Responsável",
            "Atividades sem status da atividade",
            "Atividades sem descrição",
            "Atividades sem Nº de ID",
        ],
        datasets: [
            {
                data: dataPieChartPendingTasks,
                label: "Quantidade",
                barThickness: 32,
                borderWidth: 1,
                fill: false,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(255, 159, 64, 0.2)',
                    'rgba(255, 205, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                ],
                hoverBackgroundColor: [
                    'rgb(255, 99, 132)',
                    'rgb(255, 159, 64)',
                    'rgb(255, 205, 86)',
                    'rgb(75, 192, 192)',
                    'rgb(54, 162, 235)',
                    'rgb(153, 102, 255)',
                ]
            }]
        }
    });


var ctxlineChartTasksHistoric = document.getElementById("lineChartTasksHistoric");
var lineChartTasksHistoric = new Chart(ctxlineChartTasksHistoric, {
    type: 'line',
    options: {
        responsive: true,
        maintainAspectRatio: false,
        legend: {
            display: false,
        },
    },
    data: {
        labels: [
            "Janeiro",
            "Fevereiro",
            "Março",
            "Abril",
            "Maio",
            "Junho",
            "Julho",
        ],
        datasets: [{
            label: 'My First Dataset',
            data: [65, 59, 80, 81, 56, 55, 40],
            fill: false,
            borderColor: 'rgb(75, 192, 192)',
            tension: 0.1
        }]
        }
    });
