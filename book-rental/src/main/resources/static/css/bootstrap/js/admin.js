var ctx = document.getElementById("myChart").getContext("2d");
var myChart = new Chart(ctx, {
    type: "line",
    data: {
        labels: [
            "Chủ nhật",
            "Thứ 2",
            "Thứ 3",
            "Thứ 4",
            "Thứ 5",
            "Thứ 6",
            "Thứ 7",
        ],
        datasets: [
            {
                data: [15339, 21345, 18483, 24003, 23489, 24092, 12034],
                lineTension: 0,
                backgroundColor: "transparent",
                borderColor: "#007bff",
                borderWidth: 4,
                pointBackgroundColor: "#007bff",
            },
        ],
    },
    options: {
        scales: {
            y: {
                beginAtZero: false,
            },
        },
        plugins: {
            legend: {
                display: false,
            },
        },
    },
});