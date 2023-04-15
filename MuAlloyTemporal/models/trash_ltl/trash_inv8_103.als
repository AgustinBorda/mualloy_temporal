var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop8_faulty { 
	all f:File | some link.f implies eventually ( f in Trash)
}

run  prop8_faulty