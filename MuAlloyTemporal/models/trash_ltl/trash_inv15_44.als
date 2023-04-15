var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop15_faulty { 
	 all f:File | eventually f in Trash
}

run  prop15_faulty