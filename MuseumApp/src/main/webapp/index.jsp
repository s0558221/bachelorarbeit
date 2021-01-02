<html>
<head>
    	<!-- <link rel="stylesheet" type="text/css" href="css/style.css" />-->
	    <script src="js/quizfunctions.js"></script>
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    </head>
<body>
    <h2>Jersey RESTful Web Application!</h2>
    <p><a href="rest/question">Alle Fragen</a>
    <p><a href="rest/answer">Alle Antworten</a>
    <p><a href="rest/topic">Alle Themen</a>
    <p><a href="rest/difficulty">Alle Schwierigkeiten</a>
    <p>Visit <a href="http://jersey.java.net">Project Jersey website</a>
    for more information on Jersey!
    
    <div>
    	<select name="difficulties" id="difficulties-select">
		</select>
		<script>
			getAllDifficulties()
		</script><br>
		<select name="topics" id="topics-select">
		</select>
		<script>
			getAllTopics()
		</script>
	</div>
</body>
</html>
