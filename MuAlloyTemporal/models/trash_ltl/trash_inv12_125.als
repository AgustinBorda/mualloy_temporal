var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop12_faulty { 
  eventually some f : File | f in Trash => after (always eventually f not in Trash or f in Trash)
}

run  prop12_faulty