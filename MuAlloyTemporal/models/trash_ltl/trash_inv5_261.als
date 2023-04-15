var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop5_faulty { 
	some f : Trash | eventually f not in (File + Protected + Trash)
}

run  prop5_faulty