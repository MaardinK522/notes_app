package com.mkproduction.mynotes.pojo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity( tableName = "mynotes" )
public class Note {
	@PrimaryKey( autoGenerate = true )
	private int ID = 0;

	@ColumnInfo( name = "noteTitle" )
	private String noteTitle = "";

	@ColumnInfo( name = "noteData" )
	private String noteData = "";

	@ColumnInfo( name = "noteAddedDateString" )
	private String noteAddedDate = "";

	@ColumnInfo( name = "noteIsFav" )
	private char noteIsFavourite = 'n';

	@Ignore
	public Note () {
	}

	public Note ( String noteTitle, String noteData, String noteAddedDate, char noteIsFavourite ) {
		this.noteTitle = noteTitle;
		this.noteData = noteData;
		this.noteAddedDate = noteAddedDate;
		this.noteIsFavourite = noteIsFavourite;
	}

	public int getID () {
		return ID;
	}

	public void setID ( int ID ) {
		this.ID = ID;
	}

	public String getNoteTitle () {
		return noteTitle;
	}

	public void setNoteTitle ( String noteTitle ) {
		this.noteTitle = noteTitle;
	}

	public String getNoteData () {
		return noteData;
	}

	public void setNoteData ( String noteData ) {
		this.noteData = noteData;
	}

	public String getNoteAddedDate () {
		return noteAddedDate;
	}

	public void setNoteAddedDate ( String noteAddedDate ) {
		this.noteAddedDate = noteAddedDate;
	}

	public void setIsFavourite ( char setIsFavourite ) {
		this.noteIsFavourite = setIsFavourite;
	}

	public char getNoteIsFavourite () {
		return noteIsFavourite;
	}
}
