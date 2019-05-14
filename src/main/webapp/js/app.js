// Application specific scripts

var studentIndex = 1;

function postStudent() {
	var requestData = {
		"name" : "kjh;lkj",
		"age" : 18,
		"gender" : "MALE",
		"indian" : true,
		"rollNumber" : 1,
	};
	var request = new XMLHttpRequest()
	request.open('POST', 'http://127.0.0.1:8080/rest/student');
	request.onload = function(event) {
		console.log("Inside onload status : " + event.target.status + ", readyState : " + event.target.readyState + ", statusText : " + event.target.statusText);
		// var responseData = JSON.parse(response);
		if (request.status >= 200 && request.status < 400) {
			console.log('response : ' + this.response);
		} else {
			console.log('errorResponse : ' + this.response);
		}
	};
	request.onerror = function(event, data2, data3) {
		console.log("Inside onerror status : " + event.target.status + ", readyState : " + event.target.readyState);
	};
	request.setRequestHeader('Content-Type', 'application/json')
	request.send(JSON.stringify(requestData));
}

function makeRestCall(jsonDataString) {
	// var requestData = JSON.parse(jsonDataString);
	var request = new XMLHttpRequest()
	request.open('GET', '/rest/student/process/' + studentIndex, true);
	request.onload = function(event, data2, data3) {
		console.log("Inside onload status : " + event.target.status + ", readyState : " + event.target.readyState + ", statusText : " + event.target.statusText);
		// var responseData = JSON.parse(response);
		if (request.status === 200) {
			studentIndex += 1;
		}
		if (request.status >= 200 && request.status < 400) {
			console.log('response : ' + this.response);
		} else {
			console.log('errorResponse : ' + this.response);
		}
	};
	request.onprogress = function(event, data2, data3) {
		// console.log("Inside onprogress status : " + event.target.status + ", readyState : " + event.target.readyState + ", statusText : " + event.target.statusText);
	};
	request.onerror = function(event, data2, data3) {
		console.log("Inside onerror status : " + event.target.status + ", readyState : " + event.target.readyState);
	};
	request.onreadystatechange = function(event, data2, data3) {
		// console.log("Inside onreadystatechange status : " + event.target.status + ", readyState : " + event.target.readyState);
	};
	request.onabort = function(data1, event, data3) {
		// console.log("Inside onabort status : " + event.target.status + ", readyState : " + event.target.readyState);
	};
	request.ontimeout = function(event, data2, data3) {
		// console.log("Inside ontimeout status : " + event.target.status + ", readyState : " + event.target.readyState);
	};
	request.send()
}