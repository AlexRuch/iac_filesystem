function pathList() {
    var xhttp = new XMLHttpRequest();
    var pathList, txt = "";
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            pathList = JSON.parse(this.responseText);
            txt += "<table class = \"table table-striped\">" +
                "<thead>" +
                "<tr>" +
                "<th>Дата</th>" +
                "<th>Базовая директория</th>" +
                "<th>Директорий</th>" +
                "<th>Файлов</th>" +
                "<th>Размер файлов</th>" +
                "<th></th>" +
                "</tr>" +
                "</thead>";
            for (var x in pathList) {
                txt += "<tr>" +
                    "<td>" + pathList[x].create_time + "</td>" +
                    "<td>" + pathList[x].path + "</td>" +
                    "<td>" + pathList[x].dir_count + "</td>" +
                    "<td>" + pathList[x].file_count + "</td>" +
                    "<td>" + pathList[x].files_size + "</td>" +
                    "<td><button type='button' class='btn .btn-info' data-toggle='modal' data-target='#myModal' onclick='pathFiles(" + pathList[x].path_id + ")'>Файлы</button></td>" +
                    "</tr>";
            }
            txt += "</table>";

            document.getElementById("pathList").innerHTML = txt;
        }
    };
    xhttp.open("GET", "http://localhost:28080/file_system-1.0-SNAPSHOT/path", true);
    xhttp.send();
}

window.onload = pathList();

function addPath() {
    var newPath = document.getElementById('inputPath');
    var xhttp = new XMLHttpRequest();
    var response;
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            response = this.responseText;
            if(response == "err"){
                alert("Директория не существует или у Вас нет прав доступа для её просмотра");
            }
        }
    };

    xhttp.open("POST", "http://localhost:28080/file_system-1.0-SNAPSHOT/newpath", false);
    xhttp.send(newPath.value);
    pathList();
}

function pathFiles(path_id) {
    var xhttp = new XMLHttpRequest();
    var fileList, txt = "";

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            fileList = JSON.parse(this.responseText);
            txt += "<table class = \"table table-striped\">" +
                "<thead>" +
                "<tr>" +
                "<th>Файл</th>" +
                "<th>Размер</th>" +
                "</tr>" +
                "</thead>";
            for (x in fileList) {
                txt += "<tr>" +
                    "<td>" + fileList[x].file_name + "</td>" +
                    "<td>" + fileList[x].file_size + "</td>" +
                    "</tr>"
            }
            txt += "</table>"

            document.getElementById("fileList").innerHTML = txt;
        }
    }
    xhttp.open("POST", "http://localhost:28080/file_system-1.0-SNAPSHOT/files", true);
    xhttp.send(path_id);
}