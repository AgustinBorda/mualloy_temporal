var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop9_faulty { 
	all p:Protected | after no p&Trash
}

run  prop9_faulty