package stk.mobileoffice;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import stk.mobileoffice.business.BusinessFragment;
import stk.mobileoffice.contact.ContactList;
import stk.mobileoffice.contract.ContractList;
import stk.mobileoffice.customer.CustomerList;
import stk.mobileoffice.opportunity.OpportunityList;
import stk.mobileoffice.product.ProductList;

public class MainActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener {
	private Toolbar toolbar;
	private DrawerLayout drawer;
	private NavigationView navigation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		navigation = (NavigationView) findViewById(R.id.nav_view);

		setSupportActionBar(toolbar);

		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();

		navigation.setNavigationItemSelectedListener(this);
		//Demo data
		DemoData.add_opportunity(this);
		DemoData.add_customer(this);
		DemoData.add_contract(this);
		DemoData.add_product(this);
		DemoData.add_contact(this);
		//初始界面
		getSupportFragmentManager().beginTransaction().replace(R.id.content, new OpportunityList()).commit();
	}

	@Override
	public void onBackPressed() {
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		Fragment fragment;
		switch (item.getItemId()) {
			case R.id.opportunity_menu:
				fragment = new OpportunityList();
				break;
			case R.id.customer_menu:
				fragment = new CustomerList();
				break;
			case R.id.contract_menu:
				fragment = new ContractList();
				break;
			case R.id.business_menu:
				fragment = new BusinessFragment();
				break;
			case R.id.product_menu:
				fragment = new ProductList();
				break;
			case R.id.contact_menu:
				fragment = new ContactList();
				break;
			default:
				return super.onOptionsItemSelected(item);
		}
		getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}
}
