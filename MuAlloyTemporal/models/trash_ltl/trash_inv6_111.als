var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop6_faulty { 
	always (all f:Trash | f in Trash)
}

run  prop6_faulty