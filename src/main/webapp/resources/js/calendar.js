Date.prototype.Format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
};
var firstDateOfMonth = function(date){
	var firstDateOfCurrentMonth = new Date();
	firstDateOfCurrentMonth.setTime(date.getTime()
			- (date.getDate() - 1) * 24 * 60 * 60 * 1000);
	return firstDateOfCurrentMonth;
};

var firstWeekDayOfWeek = function(date){
	var firstDateOfList = new Date();
	firstDateOfList.setTime(date.getTime()
			- (date.getDay() * 24 * 60 * 60 * 1000));
	return firstDateOfList;
};

var calendarWidget = {
	init : function(anyDayOfMonth, Y,rander,div) {
		var getFirstDateOfList = function(date) {
			return firstWeekDayOfWeek(firstDateOfMonth(date));
		};
		var firstDate = getFirstDateOfList(anyDayOfMonth);
		var dateList = Array();
		for ( var i = 0; i <= 40; i++) {
			var day = new Date();
			day.setTime(firstDate.getTime() + i * 24 * 60 * 60 * 1000);
			dateList.push(day.Format("yyyy-MM-dd"));
		}
		var now = new Date();
		initYear = now.getFullYear();
		initMonth = now.getMonth() + 1;
		var tbody = Y.Node.one(div);
		tbody.empty();
		if (dateList.length) {
			for ( var i = 0; i < 6; i++) {
				var tr = Y.Node.create('<tr>');
				for ( var j = 0; j < 7; j++) {
					var td = Y.Node.create('<td>');
					var day = dateList[(7 * i + j)];
					rander(td,day);
					tr.append(td);
				}
				tbody.append(tr);
			}
		}
	}
};