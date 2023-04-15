var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop6_faulty { 
	always ((some File) implies (always File in File))
}

run  prop6_faulty