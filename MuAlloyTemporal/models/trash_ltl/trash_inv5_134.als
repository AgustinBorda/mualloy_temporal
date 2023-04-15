var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop5_faulty { 
	always eventually #File' < #File || #File' = #File
}

run  prop5_faulty