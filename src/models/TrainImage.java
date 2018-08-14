package models;

import javafx.scene.image.ImageView;

public class TrainImage 
{
	private int id;
	private ImageView image;
	
	public TrainImage(int id, ImageView image)
	{
		this.id = id;
		this.image = image;
	}
	
	public void setImage(ImageView image)
	{
		this.image = image;
	}
	
	public ImageView getImage()
	{
		return image;
	}
	
	public int getId()
	{
		return id;
	}
}
