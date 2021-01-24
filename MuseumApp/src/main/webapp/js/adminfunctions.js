function createQRCode()
{
	var xhr = new XMLHttpRequest();
	const url = '../rest/qr/Create';

	var link =document.getElementById('linkText').value;
	
	xhr.open("POST", url, true);

	xhr.onerror = function() {
		alert("Connecting to localhost with " + url + " failed!\n");
	};

	//var obj;
	//xhr.onload = function(e) {
	//	if (this.status == 200) {
	//		var data = this.response;
	//		obj = JSON.parse(data);
	//		console.log(obj);
	//	} else {
	//		handleHttpStatus("contact", this.status);
	//	}
	//};
	xhr.send(JSON.stringify(link));

	
	document.getElementById("linkText").value ='';
}

function addTopic()
{
	console.log("IN Contact");
	
	var xhr = new XMLHttpRequest();
	const url = '../rest/topic/Add';

	var topic={
		text:document.getElementById('newTopic').value
	};
	
	xhr.open("POST", url, true);

	xhr.onerror = function() {
		alert("Connecting to localhost with " + url + " failed!\n");
	};

	//var obj;
	//xhr.onload = function(e) {
	//	if (this.status == 200) {
	//		var data = this.response;
	//		obj = JSON.parse(data);
	//		console.log(obj);
	//	} else {
	//		handleHttpStatus("contact", this.status);
	//	}
	//};
	xhr.send(JSON.stringify(topic));

	
	document.getElementById("newTopic").value ='';
}

function getAllTopics(optionName) {

let dropdown = document.getElementById(optionName);
dropdown.length = 0;

let defaultOption = document.createElement('option');
defaultOption.text = 'Bitte Themenbereich wählen';

dropdown.add(defaultOption);
dropdown.selectedIndex = 0;

const url = '../rest/topic';

const request = new XMLHttpRequest();
request.open('GET', url, true);

request.onload = function() {
  if (request.status === 200) {
    const data = JSON.parse(request.responseText);
    let option;
    for (let i = 0; i < data.length; i++) {
      option = document.createElement('option');
      option.text = data[i].text;
      option.value = data[i].id;
      dropdown.add(option);
    }
   } else {
    // Reached the server, but it returned an error
  }   
}

request.onerror = function() {
  console.error('An error occurred fetching the JSON from ' + url);
};

request.send();
}