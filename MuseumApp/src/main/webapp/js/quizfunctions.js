var questions=[];
var questionIndex = 0;
var actualQuestion=[];
var answers=[];

function getAllDifficulties() {
	
let dropdown = document.getElementById('difficulties-select');
dropdown.length = 0;

let defaultOption = document.createElement('option');
defaultOption.text = 'Bitte Schwierigkeit wählen';

dropdown.add(defaultOption);
dropdown.selectedIndex = 0;

const url = 'rest/difficulty';

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

function getAllTopics() {
	
let dropdown = document.getElementById('topics-select');
dropdown.length = 0;

let defaultOption = document.createElement('option');
defaultOption.text = 'Bitte Themenbereich wählen';

dropdown.add(defaultOption);
dropdown.selectedIndex = 0;

const url = 'rest/topic';

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

function getAnswersByQuestionID(questionId) {
	
var url = 'rest/answer/FindByQuestionId?';
url = url + "questionid=" + questionId;

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
			radio.value=answer['isCorrect'];
			let label =  document.getElementById('answerALabel');
			label.innerHTML=answers[i].text;
		}
		if(i==1)
		{
			let radio = document.getElementById('answerB');
			answer = answers[i];
			radio.value=answer['isCorrect'];
			let label =  document.getElementById('answerBLabel');
			label.innerHTML=answers[i].text;
		}
		if(i==2)
		{
			let radio = document.getElementById('answerC');
			answer = answers[i];
			radio.value=answer['isCorrect'];
			let label =  document.getElementById('answerCLabel');
			label.innerHTML=answers[i].text;
		}
		if(i==3)
		{
			let radio = document.getElementById('answerD');
			answer = answers[i];
			radio.value=answer['isCorrect'];
			let label =  document.getElementById('answerDLabel');
			label.innerHTML=answers[i].text;
		}
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

function getQuestionsByDifficultyAndTopic(){
var difficulty = document.getElementById('difficulties-select').value;
var topic = document.getElementById('topics-select').value;

var url = 'rest/question/FindByTopicIdAndDifficultyId?';
url = url + "topicId=" + topic + "&difficultyId="+difficulty;

const request = new XMLHttpRequest();
request.open('GET', url, true);

request.onload = function() {
  if (request.status === 200) {
    questions = JSON.parse(request.responseText);
   } else {
    // Reached the server, but it returned an error
  }   
}

request.onerror = function() {
  console.error('An error occurred fetching the JSON from ' + url);
};

request.send();
}

function loadQuestions(){
	getQuestionsByDifficultyAndTopic();
}

function startQuiz(){
	questionIndex =0;
	actualQuestion = questions[questionIndex];
	displayNextQuestion();
	getAnswersByQuestionID(actualQuestion.id)
	document.getElementById("play-area").style.visibility="visible";
}

function getNextQuestion(){
	questionIndex++;
	actualQuestion = questions[questionIndex];
	displayNextQuestion();
	getAnswersByQuestionID(actualQuestion.id);
	document.getElementById("nextQuestionBtn").style.visibility="hidden";
}

function displayNextQuestion(){
	let h4 = document.getElementById('question-header');
	h4.innerText = actualQuestion.text;
}

function checkAnswer(){
	var answer = document.querySelector('input[name = "answers"]:checked').value;
	if(answer=='true')
	{	
		alert("Korrekte Antwort");
		if(questionIndex<questions.length-1)
		{
			document.getElementById("nextQuestionBtn").style.visibility="visible";
		}
		else
		{
			alert("Es wurden alle Fragen korrekt beantwortet. Das Spiel ist nun vorbei.")
			gameEnd();
		}
	}
	else
	{
		alert("falsche Antwort. Das Quiz ist nun vorbei.");
		gameEnd();
	}
}

function gameEnd()
{
		document.getElementById("play-area").style.visibility="hidden";
		var ele = document.getElementsByName("answers");
   		for(var i=0;i<ele.length;i++)
      	ele[i].checked = false;
}