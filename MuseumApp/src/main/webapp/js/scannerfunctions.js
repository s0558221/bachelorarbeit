function scanNewCode(){
			document.getElementById("scanner").style.visibility="visible";
			document.getElementById("imageArea").style.visibility="hidden";
			document.getElementById("image").style.visibility="hidden";
			document.getElementById("image").src="";
			document.getElementById("ergebnisse").style.visibility="hidden";
			
			var videoElement = document.getElementById('video');
			videoElement.pause();
			videoElement.removeAttribute('src');
			videoElement.load();
			videoElement.style.visibility="hidden";
			
			document.getElementById("pdfArea").style.visibility="hidden";
			document.getElementById("pdfObject").style.visibility="hidden";
		}
		
function startScanner(){
	var scanner = new Instascan.Scanner({ video: document.getElementById('preview'), scanPeriod: 5, mirror: false });
    scanner.addListener('scan',function(content){
    	if(content.substring(0,4)=='http')
		{
			window.location.href=content;
		}else
		{
			document.getElementById('scanner').style.visibility="hidden";
			
			
			var url = '../rest/upload/GetByName?';
			url = url + 'name='+content;

			var request = new XMLHttpRequest();
			request.open('GET', url, true);
			
			request.responseType = 'arraybuffer';		
			
			request.onload = function() { 
				if(content.endsWith('jpg') || content.endsWith('png')|| content.endsWith('bmp')){
					document.getElementById('ergebnisse').style.visibility="visible";
					var blb = new Blob([request.response], {type: 'image/jpg'});
			    	var url = (window.URL || window.webkitURL).createObjectURL(blb);
			    	document.getElementById('image').style.visibility="visible";
			    	image.width = $(document).width();
			    	image.src=url;
			    }
			    else if(content.endsWith('mp4')){
					document.getElementById('ergebnisse').style.visibility="visible";
					var blb = new Blob([request.response], {type: 'video/mp4'});
			    	var url = (window.URL || window.webkitURL).createObjectURL(blb);
			    	document.getElementById('video').style.visibility="visible";
			    	video.width = $(document).width();
			    	video.src=url;
			    }
				else if(content.endsWith('pdf')){
					var blb = new Blob([request.response], {type: 'application/pdf'});
			    	var url = (window.URL || window.webkitURL).createObjectURL(blb);
					window.open(url);
  					return false;
			    }
			}
			request.onerror = function() {
			  console.error('An error occurred fetching the file from ' + url);
			};
			request.send();
		}
    });
    Instascan.Camera.getCameras().then(function (cameras){
        if(cameras.length>0){
            scanner.start(cameras[0]);
            $('[name="cameras"]').on('change',function(){
                if($(this).val()==0){
                    if(cameras[0]!=""){
                        scanner.start(cameras[0]);
                    }else{
                        alert('keine Frontkamera vorhanden!');
                    }
                }else if($(this).val()==1){
                    if(cameras[1]!=""){
                        scanner.start(cameras[1]);
                    }else{
                        alert('keine Hauptkamera vorhanden!');
                    }
                }
            });
        }else{
            console.error('keine Kameras vorhanden');
            alert('keine Kameras vorhanden');
        }
    }).catch(function(e){
        console.error(e);
        alert(e);
    });
	
}