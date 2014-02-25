function formatShortDate(date, fullYear) {
	if (!fullYear) {
		return formatParts(date, 3);
	} else {
		return formatLongDate(date).substring(0, 10);
	}
}

function formatLongDate(date) {
	return formatParts(date, 5);
}

function formatParts(date, size) {
	var parts = [
	    "0" + date.getDate(), 
	    "0" + (date.getMonth() + 1), 
	    date.getFullYear(),
	    "0" + date.getHours(),
	    "0" + date.getMinutes()
	];
	
	for (var i=0; i<size; i++) {
		if (i != 2) {
			parts[i] = parts[i].substring(parts[i].length - 2);
		}
	}
	
	return parts[0] + "/" + parts[1] + "/" + parts[2] + (size > 3 ? " " + parts[3] + ":" + parts[4] : "");
}

function parseShortDate(string) {
	var fields = string.split("/");
	
	return new Date(parseInt(fields[2]), parseInt(fields[1]) - 1, parseInt(fields[0]));
}