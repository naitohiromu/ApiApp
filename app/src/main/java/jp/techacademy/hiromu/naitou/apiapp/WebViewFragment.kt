package jp.techacademy.hiromu.naitou.apiapp
/*
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.Moshi
import jp.techacademy.hiromu.naitou.apiapp.databinding.FragmentApiBinding
import okhttp3.*
import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
*/
class WebViewFragment{
/*
    private var _binding: FragmentApiBinding? = null
    private val binding get() = _binding!!

    private val wvAdapter by lazy { WebViewActivity() }
    private val handler = Handler(Looper.getMainLooper())

    // Fragment -> Activity にFavoriteの変更を通知する
    private var fragmentCallback: FragmentCallback? = null

    // -----追加ここから
    // 一覧に表示するShopを保持
    private var list = mutableListOf<Shop>()

    // 現在のページ
    private var page = 0

    // Apiでデータを読み込み中ですフラグ。追加ページの読み込みの時にこれがないと、連続して読み込んでしまうので、それの制御のため
    private var isLoading = false
    // -----追加ここまで

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentCallback) {
            fragmentCallback = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentApiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // ここから初期化処理を行う
        // ApiAdapterのお気に入り追加、削除用のメソッドの追加を行う
        wvAdapter.apply {
            // Adapterの処理をそのままActivityに通知する
            onClickAddFavorite = { url: String, id: String, logoImage: String, name: String ->
                fragmentCallback?.onAddFavorite(url, id, logoImage, name)
            }
            // Adapterの処理をそのままActivityに通知する
            onClickDeleteFavorite = { id: String ->
                fragmentCallback?.onDeleteFavorite(id)
                Log.d("test", id)
            }
        }
    }
*/
}