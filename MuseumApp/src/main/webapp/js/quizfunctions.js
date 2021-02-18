var questions=[];
var questionIndex = 0;
var actualQuestion=[];
var answers=[];

window.onload = function() {
	document.getElementById("topics-select").style.visibility="hidden";
	document.getElementById("topics-label").style.visibility="hidden";
	document.getElementById("quiz_start_btn").style.visibility="hidden";
	document.getElementById("finishQuizBtn").disabled=true;
	document.getElementById("play-area").style.visibility="hidden";
	document.getElementById("nextQuestionBtn").disabled=true;
};

function difficultSelect(){
	var difficulty = document.getElementById('difficulties-select').value;
	if(difficulty != null)
	{
		document.getElementById("topics-select").style.visibility="visible";
		document.getElementById("topics-label").style.visibility="visible";
	}
}

function topicSelect(){
	var topic = document.getElementById('topics-select').value;
	if(topic != null)
	{
			getQuestionsByDifficultyAndTopic();
			document.getElementById("quiz_start_btn").style.visibility="visible";
	}
}

function getAllDifficulties() {
	
let dropdown = document.getElementById('difficulties-select');
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


function getAllTopics() {

let dropdown = document.getElementById('topics-select');
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

function getAnswersByQuestionID(questionId) {
	
var url = '../rest/Antwort/FindByFragenId?';
url = url + "frage=" + questionId;

const request = new XMLHttpRequest();
request.open('GET', url, true);

request.onload = function() {
  if (request.status === 200) {
    answers = JSON.parse(request.responseText);
    for (let i = 0; i < answers.length; i++) {
	if(i==0)
		{
			let radio = document.getElementById('answerA');
			answer = answers[i];
			radio.value=answer['istKorrekt'];
			let label =  document.getElementById('answerALabel');
			label.innerHTML=answers[i].text;
		}
		if(i==1)
		{
			let radio = document.getElementById('answerB');
			answer = answers[i];
			radio.value=answer['istKorrekt'];
			let label =  document.getElementById('answerBLabel');
			label.innerHTML=answers[i].text;
		}
		if(i==2)
		{
			let radio = document.getElementById('answerC');
			answer = answers[i];
			radio.value=answer['istKorrekt'];
			let label =  document.getElementById('answerCLabel');
			label.innerHTML=answers[i].text;
		}
		if(i==3)
		{
			let radio = document.getElementById('answerD');
			answer = answers[i];
			radio.value=answer['istKorrekt'];
			let label =  document.getElementById('answerDLabel');
			label.innerHTML=answers[i].text;
		}
    }
   }  
}

request.onerror = function() {
  console.error('An error occurred fetching the JSON from ' + url);
};

request.send();
}

function getQuestionsByDifficultyAndTopic(){
var difficulty = document.getElementById('difficulties-select').value;
var topic = document.getElementById('topics-select').value;

var url = '../rest/Frage/FindByThemaUndSchwierigkeit?';
url = url + "thema=" + topic + "&schwierigkeit="+difficulty;

const request = new XMLHttpRequest();
request.open('GET', url, true);

request.onload = function() {
  if (request.status === 200) {
    questions = JSON.parse(request.responseText);
   }  
}

request.onerror = function() {
  console.error('An error occurred fetching the JSON from ' + url);
};

request.send();
}

function startQuiz(){
	if(questions.length>0)
	{
		document.getElementById("noQuestionsLabel").innerHTML ="";
		questionIndex =0;
		actualQuestion = questions[questionIndex];
		displayQuestion();
	}
	else
	{
		document.getElementById("noQuestionsLabel").innerHTML ="keine Fragen vorhanden! Bitte anderes Themengebiet wählen! ";
	}
}

function displayQuestion(){
	let h4 = document.getElementById('question-header');
	h4.innerText = actualQuestion.text;
	getAnswersByQuestionID(actualQuestion.id);
	document.getElementById("play-area").style.visibility="visible";
	document.getElementById("checkAnswerBtn").disabled = false;
}

function getNextQuestion(){
	document.getElementById("answerLabel").innerHTML ='';
	
	var ele = document.getElementsByName("answers");
   		for(var i=0;i<ele.length;i++)
      	ele[i].checked = false;
	
	questionIndex++;
	actualQuestion = questions[questionIndex];
	displayQuestion();
	document.getElementById("nextQuestionBtn").disabled=true;
}

function checkAnswer(){
	var selectedAnswer = document.querySelector('input[name = "answers"]:checked');
	var answer = selectedAnswer.value;
	var korrekt = "";
	
	document.getElementById("checkAnswerBtn").disabled=true;
	
	if(answer=='true')
	{
		document.getElementById("answerLabel").style.color ='green';
		korrekt="Diese Antwort ist korrekt.";
		if(questionIndex<questions.length-1)
		{
			korrekt+="Weiter zur nächsen Frage.";
			document.getElementById("nextQuestionBtn").disabled=false;
		}
		else
		{
			korrekt+=" Herzlichen Glückwunsch. Es wurden alle Fragen erfolgreich beantwortet.";
			document.getElementById("finishQuizBtn").disabled=false;
		}
	}
	else
	{
		document.getElementById("answerLabel").style.color ='red';
		korrekt="Diese Antwort ist falsch. Das Spiel ist nun vorbei.";
		document.getElementById("finishQuizBtn").disabled=false;
	}
	document.getElementById("answerLabel").innerHTML =korrekt;
}

function endQuiz()
{
		document.getElementById("answerLabel").innerHTML ='';
		document.getElementById("finishQuizBtn").disabled=true;
		document.getElementById("topics-select").style.visibility="hidden";
		document.getElementById("topics-label").style.visibility="hidden";
		document.getElementById("quiz_start_btn").style.visibility="hidden";
		
		let difficultiesdropdown = document.getElementById("difficulties-select");
		difficultiesdropdown.selectedIndex = 0;
		
		let topicsdropdown = document.getElementById("topics-select");
		topicsdropdown.selectedIndex = 0;
		
		var ele = document.getElementsByName("answers");
   		for(var i=0;i<ele.length;i++)
      	ele[i].checked = false;
      	
      	document.getElementById("play-area").style.visibility="hidden";
}

