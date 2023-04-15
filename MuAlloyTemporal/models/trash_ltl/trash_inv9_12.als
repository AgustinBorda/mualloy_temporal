var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop9_faulty { 
  all f: File | always f' not in Trash since f in Protected
}

run  prop9_faulty