var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop12_faulty { 
  all f: File | eventually some Trash and once f in Trash implies always f in Trash
}

run  prop12_faulty