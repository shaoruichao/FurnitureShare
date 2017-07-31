package com.example.FurnitureShare.bean;


import java.io.Serializable;

public class ProductInfo extends BaseInfo implements Serializable
{

	private String id;//购物车id
	private String imageUrl;//商品图片
	private String desc;
	private String per;

	private String shopname;//商家名字

	private String leasedays;//租借时长

	private String pay_way;//付款方式

	private String foregift;//押金

	private String leaseprice;//租金

	private String gid;

	private boolean isTop;

	/** 是否是编辑状态 */
	private boolean isEditing;

	public boolean isEditing() {
		return isEditing;
	}

	public void setEditing(boolean editing) {
		isEditing = editing;
	}

	public boolean isTop() {
		return isTop;
	}

	public void setTop(boolean top) {
		isTop = top;
	}


	public String getSrcname() {
		return srcname;
	}

	public void setSrcname(String srcname) {
		this.srcname = srcname;
	}

	private String srcname;
	private int count;
	private int position;// 绝对位置，只在ListView构造的购物车中，在删除时有效

	public ProductInfo()
	{
		super();
	}

	public ProductInfo(String id,String gid, String name, String imageUrl
			, String desc,String leasedays, String per, int count
			,String shopname,String pay_way,String foregift,String leaseprice)
	{

		this.id = id;
		this.gid = gid;
		this.srcname = name;
		this.imageUrl = imageUrl;
		this.desc = desc;
		this.per = per;
		this.count = count;
		this.leasedays = leasedays;
		this.shopname = shopname;
		this.pay_way = pay_way;
		this.foregift = foregift;
		this.leaseprice = leaseprice;
	}

	public String getLeaseprice() {
		return leaseprice;
	}

	public void setLeaseprice(String leaseprice) {
		this.leaseprice = leaseprice;
	}

	public String getForegift() {
		return foregift;
	}

	public void setForegift(String foregift) {
		this.foregift = foregift;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getPay_way() {
		return pay_way;
	}

	public void setPay_way(String pay_way) {
		this.pay_way = pay_way;
	}

	public String getLeasedays() {
		return leasedays;
	}

	public void setLeasedays(String leasedays) {
		this.leasedays = leasedays;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}


	public String getImageUrl()
	{
		return imageUrl;
	}

	public void setImageUrl(String imageUrl)
	{
		this.imageUrl = imageUrl;
	}

	public String getDesc()
	{
		return desc;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public String getPer()
	{
		return per;
	}

	public void setPer(String per)
	{
		this.per = per;
	}

	public int getPosition()
	{
		return position;
	}

	public void setPosition(int position)
	{
		this.position = position;
	}

}
