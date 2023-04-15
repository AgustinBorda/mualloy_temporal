var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop5_faulty { 
	some f:File | eventually f in Trash and f.link in Trash
}

run  prop5_faulty