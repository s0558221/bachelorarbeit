function clearQRCode()
{
	document.getElementById("linkText").value = "";
	document.getElementById("qrcode").innerHTML = "";
	document.getElementById("qrcode").title = "";
}

function createQRCode()
{
	var qrtype = document.getElementById("qrContentType").value;
	
	if(qrtype==0)
	{
		var link =document.getElementById("linkText").value;
		console.log(link);
	}
	else if(qrtype==1)
	{
		var filename = document.getElementById("qrFiles").value;
		var link = 'https://192.168.178.3:443/MuseumApp/rest/upload/GetByName?name='+filename;
	}
	new QRCode(document.getElementById("qrcode"), link);
}

function addTopic()
{
	var xhr = new XMLHttpRequest();
	const url = '../rest/Thema/Add';

	var thema={
		text:document.getElementById('newTopic').value
	};
	
	xhr.open("POST", url, true);

	xhr.onerror = function() {
		alert("Connecting to localhost with " + url + " failed!\n");
	};
	xhr.send(JSON.stringify(thema));
	document.getElementById("newTopic").value ='';
}

function getAllTopics(optionName) {

let dropdown = document.getElementById(optionName);
dropdown.length = 0;

let defaultOption = document.createElement('option');
defaultOption.text = 'Bitte Themenbereich wählen';

dropdown.add(defaultOption);
dropdown.selectedIndex = 0;

const url = '../rest/Thema';

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
  }
}

request.onerror = function() {
  console.error('An error occurred fetching the JSON from ' + url);
};

request.send();
}

function getAllDifficulties(optionName) {
	
let dropdown = document.getElementById(optionName);
dropdown.length = 0;

let defaultOption = document.createElement('option');
defaultOption.text = 'Bitte Schwierigkeit wählen';

dropdown.add(defaultOption);
dropdown.selectedIndex = 0;

const url = '../rest/Schwierigkeit';

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
   }
}

request.onerror = function() {
  console.error('An error occurred fetching the JSON from ' + url);
};

request.send();
}

function getAllQuestions(optionName) {
	
let dropdown = document.getElementById(optionName);
dropdown.length = 0;

let defaultOption = document.createElement('option');
defaultOption.text = 'Bitte Frage wählen';

dropdown.add(defaultOption);
dropdown.selectedIndex = 0;

const url = '../rest/Frage';

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
   }
}

request.onerror = function() {
  console.error('An error occurred fetching the JSON from ' + url);
};

request.send();
}

function questionSelect(){

	var questionId = document.getElementById('questions-existing').value;
	var url = '../rest/Frage/FindById?';
	url = url + "id=" + questionId;

	const request = new XMLHttpRequest();
	request.open('GET', url, true);

	request.onload = function() {
  		if (request.status === 200) {
    		question = JSON.parse(request.responseText);
    		let questionTextbox = document.getElementById("actualQuestion")
    		questionTextbox.value = question.text;
    		let topicdropdown = document.getElementById("questionTopic");
			topicdropdown.selectedIndex = question.thema;
			let difficultdropdown = document.getElementById("questionDifficulty");
			difficultdropdown.selectedIndex = question.schwierigkeit;
			let questionIdTextbox = document.getElementById("questionId");
    		questionIdTextbox.value = question.id;
			getAnswers(question.id);
   		} 
   	}
   	
   	request.onerror = function() {
  		console.error('An error occurred fetching the JSON from ' + url);
	};
	if(questionId!="Bitte Frage wählen"){
		request.send();
	}
}

function getAnswers(questionid){
var url = '../rest/Antwort/FindByFragenId?';
url = url + "frage=" + questionid;

const request = new XMLHttpRequest();
request.open('GET', url, true);

request.onload = function() {
  if (request.status === 200) {
    answers = JSON.parse(request.responseText);
    for (let i = 0; i < answers.length; i++) {
		if(i==0)
		{
			answer = answers[i];
			let textA = document.getElementById('answerA');
			textA.value = answer['text'];
			let aCorrect = document.getElementById('answerAcorrect');
			aCorrect.checked  = answer['istKorrekt'];
			answerAid.value=answer['id'];
		}
		if(i==1)
		{
			answer = answers[i];
			let textB = document.getElementById('answerB');
			textB.value = answer['text'];
			let bCorrect = document.getElementById('answerBcorrect');
			bCorrect.checked  = answer['istKorrekt'];
			answerBid.value=answer['id'];
		}
		if(i==2)
		{
			answer = answers[i];
			let textC = document.getElementById('answerC');
			textC.value = answer['text'];
			let cCorrect = document.getElementById('answerCcorrect');
			cCorrect.checked  = answer['istKorrekt'];
			answerCid.value=answer['id'];
		}
		if(i==3)
		{
			answer = answers[i];
			let textD = document.getElementById('answerD');
			textD.value = answer['text'];
			let dCorrect = document.getElementById('answerDcorrect');
			dCorrect.checked  = answer['istKorrekt'];
			answerDid.value=answer['id'];
		}
    }
   }
}

request.onerror = function() {
  console.error('An error occurred fetching the JSON from ' + url);
};

request.send();
}

function saveQuestion(){
	var question={
		id:document.getElementById('questionId').value,
		text:document.getElementById('actualQuestion').value,
		schwierigkeit:document.getElementById('questionDifficulty').value,
		thema:document.getElementById('questionTopic').value
	};
	
	var xhr = new XMLHttpRequest();
	const url = '../rest/Frage/Add';
	
	xhr.open("POST", url, true);

	xhr.onload = function() {
  	if (xhr.status === 200) {
    var questionId = JSON.parse(xhr.responseText);
    saveAnswer(questionId,document.getElementById('answerA').value,document.getElementById('answerAcorrect').checked,document.getElementById('answerAid').value);
   	saveAnswer(questionId,document.getElementById('answerB').value,document.getElementById('answerBcorrect').checked,document.getElementById('answerBid').value);
   	saveAnswer(questionId,document.getElementById('answerC').value,document.getElementById('answerCcorrect').checked,document.getElementById('answerCid').value);
   	saveAnswer(questionId,document.getElementById('answerD').value,document.getElementById('answerDcorrect').checked,document.getElementById('answerDid').value);
   	}
   	}

	xhr.onerror = function() {
		alert("Connecting to localhost with " + url + " failed!\n");
	};
	xhr.send(JSON.stringify(question));
}

function saveAnswer(questionId,answerText,answerCorrect,answerId)
{
	var answer={
		id:answerId,
		text:answerText,
		id_frage:questionId,
		istKorrekt:answerCorrect
	};
	
	var xhr = new XMLHttpRequest();
	const url = '../rest/Antwort/Add';
	
	xhr.open("POST", url, true);

	xhr.onerror = function() {
		alert("Connecting to localhost with " + url + " failed!\n");
	};
	xhr.send(JSON.stringify(answer));
}

function createNewQuestion(){
	let questionIdTextbox = document.getElementById("questionId");
    questionIdTextbox.value = "-1";
    let questionTextbox = document.getElementById("actualQuestion")
    questionTextbox.value = "";
    
    let questionsdropdown = document.getElementById("questions-existing");
	questionsdropdown.selectedIndex = 0;
    
    let topicsdropdown = document.getElementById("questionTopic");
	topicsdropdown.selectedIndex = 0;
		
	let difficultiesdropdown = document.getElementById("questionDifficulty");
	difficultiesdropdown.selectedIndex = 0;
	
	let answerAText = document.getElementById("answerA");
    answerAText.value = "";
    let answerBText = document.getElementById("answerB");
    answerBText.value = "";
    let answerCText = document.getElementById("answerC");
    answerCText.value = "";
    let answerDText = document.getElementById("answerD");
    answerDText.value = "";
    
    let answerAid = document.getElementById("answerAid");
    answerAid.value = "-1";
    let answerBid = document.getElementById("answerBid");
    answerBid.value = "-1";
    let answerCid = document.getElementById("answerCid");
    answerCid.value = "-1";
    let answerDid = document.getElementById("answerDid");
    answerDid.value = "-1";
    
    let answerAcorrect = document.getElementById("answerAcorrect");
    answerAcorrect.checked = false;
    let answerBcorrect = document.getElementById("answerBcorrect");
    answerBcorrect.checked = false;
    let answerCcorrect = document.getElementById("answerCcorrect");
    answerCcorrect.checked = false;
    let answerDcorrect = document.getElementById("answerDcorrect");
    answerDcorrect.checked = false;
}

function uploadFile(){
	var file = document.getElementById("file").files[0];
	var form = new FormData();
    form.append("file", file);
    
    var xhr = new XMLHttpRequest();
	const url = '../rest/upload/file';
	
	xhr.open("POST", url, true);
	xhr.onload = function() {
  		if (xhr.status === 200) {
  			console.log("Test");
    	}
   	}
   	
	xhr.onerror = function() {
		alert("Connecting to localhost with " + url + " failed!\n");
	};
	xhr.send(form);
}

function changeQRContentType(){
	var contentType = document.getElementById("qrContentType").value;
	if(contentType!=-1)
	{
		if(contentType==1)
		{
			var div = document.getElementById("createQRWebsite");
			div.style.display = 'none';
			div = document.getElementById("createQRFile");
			div.style.display = 'inline-block';
		}else
		{
			var div = document.getElementById("createQRWebsite");
			div.style.display = 'inline-block';
			div = document.getElementById("createQRFile");
			div.style.display = 'none';
		}
	}else
	{
		var div = document.getElementById("createQRWebsite");
		div.style.display = 'none';
		div = document.getElementById("createQRFile");
		div.style.display = 'none';
	}
}


function getUploadedFileNames(){
let dropdown = document.getElementById("qrFiles");
dropdown.length = 0;

const url = '../rest/upload/GetFileList';

const request = new XMLHttpRequest();
request.open('GET', url, true);

request.onload = function() { 

if (request.status === 200) {
    const data = JSON.parse(request.responseText);
    for (let i = 0; i < data.length; i++) {
    	option = document.createElement('option');
      	option.text = data[i];
      	option.value = data[i];
      	dropdown.add(option);
   }
  }
}

request.onerror = function() {
  console.error('An error occurred fetching the JSON from ' + url);
};

request.send();
}