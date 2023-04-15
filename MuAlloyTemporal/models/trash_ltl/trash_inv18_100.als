var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop18_faulty { 
	always (all f:Protected | f in Trash-Protected triggered f in Protected)
}

run  prop18_faulty