var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop8_faulty { 
	 all f:File | eventually f.link not in File'
}

run  prop8_faulty