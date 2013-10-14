function Controller($scope, $http) {

	 google.setOnLoadCallback(drawChartOnLoad);
	  
	 function drawChartOnLoad() {
		$http.get('/rest/client/nonfin').success(
				function(data) {
					$scope.nonfinDatas = data;
					$scope.listNonFinInstruments = _.uniq(_.pluck($scope.nonfinDatas, 'name'));
					$scope.listNonFinInstrument = $scope.listNonFinInstruments[0];
					
					$scope.currentSelectedInstrumentList = _.where($scope.nonfinDatas, {name: $scope.listNonFinInstrument});
					var dataToDraw = [['Date', 'Producers change', 'Swap dealers change', 'Managed money change', 'Other reportable change']];
					
					angular.forEach($scope.currentSelectedInstrumentList, function(instrument){
			    		delete  instrument.name;
			    		delete instrument.exchangeName;
			    		var obj = [];
			    		
			    		date = instrument.date;
			    		producersChange = instrument.producersChange;
			    		swapDealersChange = instrument.swapDealersChange;
			    		managedMoneyChange =instrument.managedMoneyChange;
			    		otherReportableChange = instrument.otherReportableChange;
			    		
			    		obj.push(date, producersChange, swapDealersChange,managedMoneyChange,otherReportableChange);
			    		dataToDraw.push(obj);
			    	});
					
					var listToDraw = google.visualization.arrayToDataTable(dataToDraw);

					var options = {
						  title :   $scope.listNonFinInstrument,
						  curveType: "function"
					};
					
					 
				    var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
				        chart.draw(listToDraw, options);
				});
    }

	$scope.drawInstrumentChart  = function(){
						$scope.currentSelectedInstrumentList = _.where($scope.nonfinDatas, {name: $scope.listNonFinInstrument});
						var dataToDraw = [['Date', 'Producers change', 'Swap dealers change', 'Managed money change', 'Other reportable change']];
						
						angular.forEach($scope.currentSelectedInstrumentList, function(instrument){
				    		var obj = [];
				    		
				    		date = instrument.date;
				    		producersChange = instrument.producersChange;
				    		swapDealersChange = instrument.swapDealersChange;
				    		managedMoneyChange =instrument.managedMoneyChange;
				    		otherReportableChange = instrument.otherReportableChange;
				    		
				    		obj.push(date, producersChange, swapDealersChange,managedMoneyChange,otherReportableChange);
				    		dataToDraw.push(obj);
				    	});
						
						var listToDraw = google.visualization.arrayToDataTable(dataToDraw);

						var options = {
							  title :   $scope.listNonFinInstrument,
							  curveType: "function"
						};
						 
					    var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
					        chart.draw(listToDraw, options);
	 };
}
