var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop8_faulty { 
	all f1:File , f2:File | f1.link = f2 
}

run  prop8_faulty