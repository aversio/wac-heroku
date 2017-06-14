function initpage() {
	// Functie voor het laden van ISP gegevens
	loadISPdata();

	// Toon standaard weergegevens
	showWeather(52, 5, 'Utrecht');
	
	// Functie voor het laden van landen
	loadCountries();
}

function loadISPdata() {
	$(document).ready(function() {
		$.ajax({
			url: 'http://ip-api.com/json',
			dataType: 'json',
			success: function(data) {
				$("#as").append(data.as);
				$("#city").append(data.city); 			
				$("#country").append(data.country); 	
				$("#countryCode").append(data.countryCode);
				$("#isp").append(data.isp); 			
				$("#lat").append(data.lat); 			
				$("#lon").append(data.lon); 			
				$("#org").append(data.org); 			
				$("#query").append(data.query); 		
				$("#region").append(data.region); 		
				$("#regionName").append(data.regionName); 
				$("#status").append(data.status); 		
				$("#timezone").append(data.timezone); 	
				$("#zip").append(data.zip);
			},
			 error: function() {
				alert('error getting JSON about location');
			}
		});
	});
}

function showWeather(latitude, longitude, city) {
	$(document).ready(function() {
		$.ajax({
			url: 'http://api.openweathermap.org/data/2.5/weather?lat='+ latitude +'&lon='+ longitude +'&q='+ city +'&APPID=68b603c4f25b5be3f4fb21244142b20a',
			dataType: 'json',
			success: function(data) {
				$("#main").append(data.name);
				
				var celcius = data.main.temp - 273.35;
				var celFixed = celcius.toFixed(2);
				$("#celcius").append("Graden: "+ celFixed + "ºC");
				
				var kmPerHour = data.wind.speed * 3.6;
				var kmFixed = kmPerHour.toFixed(2);
				$("#snelheid").append("Windsnelheid: "+ kmFixed + " km/u");
			},
			 error: function() {
				alert('error getting JSON about weather');
			}
		});
	});
}

function clearWeathers() {
	$("#main").html("");
	$("#celcius").html("");
	$("#snelheid").html("");
}

function resetWeather() {
	clearWeathers();
	showWeather(52, 5, 'Utrecht')
}

function loadCountries() {
	$(document).ready(function() {
		$.ajax({
			url: '/rest/countries',
			dataType: 'json',
			success: function(data) {
				var inData = '';
				$.each(data, function(c, country) {
					
					inData += 
					"<tr>" +
						"<td>"+ country.id +"</td>" +
						"<td>"+ country.country +"</td>" +
						"<td>"+ country.region +"</td>" +
						"<td>"+ country.surface +"</td>" +
						"<td>"+ country.population +"</td>" +
						"<td>"+ country.latitude +"</td>" +
						"<td>"+ country.longitude +"</td>" +
					"</tr>"				

				});
				
				$('#records_table').on("click", "#tablebody tr", function(e) {
					lat = $(':nth-child(6)',  this).text();
					lon = $(':nth-child(7)',  this).text();
					clearWeathers();
					showWeather(lat, lon, "Beijing");
				});
				
				$('#records_table > tbody').append(inData);
				
			},
			 error: function() {
			   alert("error getting data from countries!")
			}
		});
	});
}


