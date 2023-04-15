var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop10_faulty { 
  always all f: File | always (once f in Protected implies always f in Protected)
}

run  prop10_faulty