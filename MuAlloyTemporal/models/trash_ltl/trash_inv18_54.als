var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop18_faulty { 
	 all p:Protected | always p in File - Protected iff p'=Trash
}

run  prop18_faulty