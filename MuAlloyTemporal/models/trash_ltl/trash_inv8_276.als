var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop8_faulty { 
	always (all f,f2:File | f->f2 in link implies eventually ( f in Trash and f2 in Trash))
}

run  prop8_faulty