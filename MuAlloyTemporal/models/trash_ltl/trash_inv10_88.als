var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop10_faulty { 
	
	always no Protected & Trash
}

run  prop10_faulty