var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop17_faulty { 
    always no Trash & Trash'
}

run  prop17_faulty