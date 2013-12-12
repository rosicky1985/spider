var server = {
	getTasks : function(day) {
		return [ {
			id : 123,
			created : '2013-10-10 12:00:01',
			target : '2013-10-09 12:00:01',
			types : [ {
				name : '日报'
			}, {
				name : '周报'
			} ]
		}, {
			id : 123,
			created : '2013-10-10 13:00:01',
			target : '2013-10-09 13:00:01',
			types : [ {
				name : '日报'
			}, {
				name : '周报'
			}, {
				name : '月报'
			} ]
		} ];
	}
};
YUI().use('node', "json-stringify", "json-parse", "io", function(Y) {
	var currentDay = null;
	var nextMonth = function() {
		currentDay.setTime(currentDay.getTime() + 30 * 24 * 60 * 60 * 1000);// TODO
		calendarWidget.init(currentDay, Y, rendarDay);
	};
	var lastMonth = function() {
		currentDay.setTime(currentDay.getTime() - 30 * 24 * 60 * 60 * 1000);// TODO
		calendarWidget.init(currentDay, Y, rendarDay);
	};
	var widgets = {
		pre : Y.one("#pre"),
		next : Y.one("#next"),
		init : function() {
			pre.on('click', lastMonth);
			next.on('click', nextMonth);
		}
	};
	var parseDate = function(s) {
		if (s.length == 0)
			return null;
		var arr = s.split("-");
		return new Date(Date.parse(arr[1] + '/' + arr[2] + '/' + arr[0]));
	};
	var rendarDay = function(td, day) {
		var tasks = server.getTasks(day);
		var dayDiv = Y.Node.create('<div>');
		var dateTitleDiv = Y.Node.create('<div>');
		var month = parseDate(day).getMonth() + 1;
		dateTitleDiv.setHTML(month + "月" + parseDate(day).getDate() + "日");
		dateTitleDiv.addClass('day');
		dayDiv.append(dateTitleDiv);
		for ( var i = 0; i < tasks.length; i++) {
			var task = tasks[i];
			var taskDiv = Y.Node.create('<div>');
			dayDiv.append(taskDiv);
			var createdSpan = Y.Node.create('<span>');
			taskDiv.append(createdSpan);
			createdSpan.setHTML(task.created);
			var typeUl = Y.Node.create('<ul>');
			dayDiv.append(typeUl);
			for ( var j = 0; j < task.types.length; j++) {
				var type = task.types[j];
				var typeli = Y.Node.create('<li>');
				typeli.setHTML(type.name);
				typeUl.append(typeli);
			}
		}
		td.append(dayDiv);
	};
	var init = function() {
		var now = new Date();
		currentDay = now;
		calendarWidget.init(currentDay, Y, rendarDay, "tbody");
		widgets.init();
	};

	Y.on("domready", init);
});