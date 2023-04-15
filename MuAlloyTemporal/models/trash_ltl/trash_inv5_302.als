var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop5_faulty { 
  eventually (Trash & File = Trash)
}

run  prop5_faulty