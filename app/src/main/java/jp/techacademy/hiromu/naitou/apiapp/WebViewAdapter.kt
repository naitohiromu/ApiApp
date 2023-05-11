package jp.techacademy.hiromu.naitou.apiapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import jp.techacademy.hiromu.naitou.apiapp.databinding.RecyclerFavoriteBinding

class WebViewAdapter  : ListAdapter<Shop, WebItemViewHolder>(ApiItemCallback()) {
    // 一覧画面から登録するときのコールバック（FavoriteFragmentへ通知するメソッド)
    var onClickAddFavorite: ((String,String,String,String) -> Unit)? = null

    // 一覧画面から削除するときのコールバック（ApiFragmentへ通知するメソッド)
    var onClickDeleteFavorite: ((String) -> Unit)? = null
    /**
     * ViewHolderを生成して返す
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebItemViewHolder {
        // ViewBindingを引数にApiItemViewHolderを生成
        val view =
            RecyclerFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WebItemViewHolder(view)
    }

    /**
     * 指定された位置（position）のViewにShopの情報をセットする
     */
    override fun onBindViewHolder(holder: WebItemViewHolder, position: Int) {
        holder.bind(getItem(position), position, this)
    }
}

/**
 * リスト内の1行の内容を保持する
 */
class WebItemViewHolder(private val binding: RecyclerFavoriteBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(shop: Shop, position: Int, adapter: WebViewAdapter) {
        binding.rootView.apply {
            // 偶数番目と奇数番目で背景色を変更させる
            binding.rootView.setBackgroundColor(
                ContextCompat.getColor(
                    binding.rootView.context,
                    if (position % 2 == 0) android.R.color.white else android.R.color.darker_gray
                )
            )
        }

        // 1行の項目にShopの値をセット
        // nameTextViewのtextプロパティに代入されたオブジェクトのnameプロパティを代入
        binding.nameTextView.text = shop.name

        // Picassoライブラリを使い、imageViewにdata.logoImageのurlの画像を読み込ませる
        Picasso.get().load(shop.logoImage).into(binding.imageView)

        // 星の処理
        binding.favoriteImageView.apply {
            // お気に入り状態を取得
            val isFavorite = FavoriteShop.findBy(shop.id) != null

            // 白抜きの星を設定
            setImageResource(if (isFavorite) R.drawable.ic_star else R.drawable.ic_star_border)

            // 星をタップした時の処理
            setOnClickListener {
                if (isFavorite) {
                    adapter.onClickDeleteFavorite?.invoke(shop.id)
                } else {
                    adapter.onClickAddFavorite?.invoke(shop.couponUrls.sp.ifEmpty { shop.couponUrls.pc },shop.id,shop.logoImage,shop.name)
                }
                adapter.notifyItemChanged(position)
            }
        }
    }
}

/**
 * データの差分を確認するクラス
 */
internal class WebItemCallback : DiffUtil.ItemCallback<Shop>() {

    override fun areItemsTheSame(oldItem: Shop, newItem: Shop): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Shop, newItem: Shop): Boolean {
        return oldItem == newItem
    }
}