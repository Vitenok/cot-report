function Controller($scope, $http) {
	
	$http.get('/').success(function(data) {
		$scope.cotDatas = data;
	});
	
	instrumentsToShow = [];
	
	$scope.cotDataToShow = function(){
	
		angular.forEach($scope.cotDatas, function(instrumentToShow){
			instrumentToShow.name = $scope.cotDatas.name;
			instrumentToShow.exchangeName = $scope.cotDatas.exchangeName;
			instrumentsToShow.push(instrumentToShow);
		});
		return  $scope.uniqueInstrumentsToShow = _.uniq(instrumentsToShow);
	};
		
	google.load("visualization", "1", {
		packages : [ "corechart" ]
	});
	
	
	function drawChart(name) {
		var data = google.visualization.arrayToDataTable([
				[ 'Date', 'producersChange', 'swapDealersChange', 'managedMoneyChange' , 'otherReportableChange'], [ '2004', 1000, 400, 0,0],
				[ '2005', 1170, 460, 0,0], [ '2006', 660, 1120, 0,0],
				[ '2007', 1030, 540, 0,0 ] ]);

		var options = {
			title : 'CoT Report'
		};

		var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
		chart.draw(data, options);
	}
}