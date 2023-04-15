var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop14_faulty { 
	eventually some Protected & Trash => no Protected' & Trash'
}

run  prop14_faulty