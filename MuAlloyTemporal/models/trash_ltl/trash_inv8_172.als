var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop8_faulty { 
	always eventually all f:File | f in link.f implies link.f in Trash
}

run  prop8_faulty