package jp.techacademy.hiromu.naitou.apiapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import android.util.Log
import androidx.appcompat.app.AlertDialog
import jp.techacademy.hiromu.naitou.apiapp.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() , FragmentCallback{
    private lateinit var binding: ActivityWebViewBinding
    //private val apiAdapter by lazy { ApiAdapter() }
    // 一覧画面から登録するときのコールバック（FavoriteFragmentへ通知するメソッド)
    var onClickAddFavorite: ((String,String,String,String) -> Unit)? = null

    // 一覧画面から削除するときのコールバック（ApiFragmentへ通知するメソッド)
    var onClickDeleteFavorite: ((String) -> Unit)? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra(KEY_URL).toString()
        binding.webView.loadUrl(url)

        val name = intent.getStringExtra("name").toString()
        binding.nameTextViewTab.text = intent.getStringExtra("name")

        val logoImage = intent.getStringExtra("logoImage").toString()
        Picasso.get().load(logoImage).into(binding.imageViewTab)

        binding.favoriteImageViewTab.apply{
            val id = intent.getStringExtra("id").toString()
            val isFavorite = FavoriteShop.findBy(id) != null

            // 白抜きの星を設定
            setImageResource(if (isFavorite) R.drawable.ic_star else R.drawable.ic_star_border)
            setOnClickListener{
                Log.d("test",name)

                if(isFavorite){
                    onClickDeleteFavorite?.invoke(id)
                    Log.d("test",isFavorite.toString())
                } else{
                    onClickAddFavorite?.invoke(url,id,logoImage,name)
                    Log.d("test",isFavorite.toString())
                }


            }
        }

    /*
        // nameTextViewのtextプロパティに代入されたオブジェクトのnameプロパティを代入
        binding.nameTextView.text = favoriteShop.name

        // Picassoというライブラリを使ってImageVIewに画像をはめ込む
        Picasso.get().load(favoriteShop.imageUrl).into(binding.imageView)
        */
    }

    override fun onClickItem(url: String, id: String, logoImage: String, name: String) {
        TODO("Not yet implemented")
    }

    override fun onAddFavorite(f_url:String,f_id:String,logoImage:String,f_name:String) {
        FavoriteShop.insert(FavoriteShop().apply {
            id = f_id
            name = f_name
            imageUrl = logoImage
            url = f_url
        })
        Log.d("test",f_name)
    }

    override fun onDeleteFavorite(id: String) {
        showConfirmDeleteFavoriteDialog(id)
        Log.d("test",id)
    }

    private fun showConfirmDeleteFavoriteDialog(id: String) {
        AlertDialog.Builder(this)
            .setTitle(R.string.delete_favorite_dialog_title)
            .setMessage(R.string.delete_favorite_dialog_message)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                deleteFavorite(id)
                /*
                if (binding.tabLayout.selectedTabPosition == MainActivity.VIEW_PAGER_POSITION_FAVORITE) {
                    showFavoriteTabInfo(binding.tabLayout.getTabAt(binding.tabLayout.selectedTabPosition)!!)
                }

                 */
            }
            .setNegativeButton(android.R.string.cancel) { _, _ -> }
            .create()
            .show()
    }

    private fun deleteFavorite(id: String) {
        FavoriteShop.delete(id)
        //(viewPagerAdapter.fragments[MainActivity.VIEW_PAGER_POSITION_API] as ApiFragment).updateView()
        //(viewPagerAdapter.fragments[MainActivity.VIEW_PAGER_POSITION_FAVORITE] as FavoriteFragment).updateData()
    }



    companion object {
        private const val KEY_URL = "key_url"
        private const val VIEW_PAGER_POSITION_API = 0
        private const val VIEW_PAGER_POSITION_FAVORITE = 1
        fun start(activity: Activity, url:String,id:String,logoImage:String,name:String) {
            val intent = Intent(activity,WebViewActivity::class.java)
            intent.putExtra(KEY_URL,url)
            intent.putExtra("id",id)
            intent.putExtra("logoImage",logoImage)
            intent.putExtra("name",name)
            activity.startActivity(intent)
            /*
            activity.startActivity(
                Intent(activity, WebViewActivity::class.java).putExtra(
                    KEY_URL,
                    url
                )
            )
             */
        }
    }
}