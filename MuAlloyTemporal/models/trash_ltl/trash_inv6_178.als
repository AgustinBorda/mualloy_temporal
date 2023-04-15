var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop6_faulty { 
	always some f:File | (not (always f in Trash)) releases (f in Trash and after f in Trash)
}

run  prop6_faulty