var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop18_faulty { 
 	all f:Protected | after f not in Protected implies f in Trash
}

run  prop18_faulty