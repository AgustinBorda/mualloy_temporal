var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop17_faulty { 
	always (some f:Trash | after no Trash&f)
}

run  prop17_faulty