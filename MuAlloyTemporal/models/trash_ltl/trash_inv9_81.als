var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop9_faulty { 
  all f: File | eventually after f in Protected implies always f not in Trash
}

run  prop9_faulty