package pe.edu.upc.coffeecompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import pe.edu.upc.coffeecompose.data.model.Coffee
import pe.edu.upc.coffeecompose.ui.theme.CoffeeComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeeComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    CoffeeList()
                }
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun CoffeeList() {
    val coffees = Coffee.mock()
    val lazyListState = rememberLazyListState()
    val visibleItemIndex = lazyListState.firstVisibleItemIndex
    val visibleItemScrollOffset = lazyListState.firstVisibleItemScrollOffset
    Log.d(
        "LazyListState",
        "VisibleItemIndex: $visibleItemIndex, VisibleItemScrollOffset: $visibleItemScrollOffset"
    )

    val pagerState = rememberPagerState()
    val currentPage = pagerState.currentPage
    val currentPageOffset = pagerState.currentPageOffset

    Log.d(
        "PagerState",
        "CurrentPage: $currentPage, CurrentPageOffset: $currentPageOffset"
    )

    BoxWithConstraints {
        val width = maxWidth / 2
        val height = maxHeight / 2

        val factor = 1 - visibleItemScrollOffset / with(LocalDensity.current) { height.toPx() }

        val pageFactor = 1 - currentPageOffset

        Column {
            VerticalPager(
                coffees.size,
                state = pagerState,
                modifier = Modifier.weight(0.5f)
            ) { page ->

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Image(
                        painter = rememberImagePainter(coffees[page].poster),
                        contentDescription = null,
                        modifier = Modifier
                            .size(
                                if (page == currentPage) width * pageFactor else width,
                                height
                            )
                            .alpha(if (page == currentPage) pageFactor else 1.0f)

                    )
                }

            }

            LazyColumn(
                state = lazyListState,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(0.5f)
            ) {
                items(coffees.size) { index ->
                    val color = if (index % 2 == 0) Color.Red else Color.Green

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color)
                    ) {
                        Image(
                            painter = rememberImagePainter(coffees[index].poster),
                            contentDescription = null,
                            modifier = Modifier
                                .size(
                                    if (index == visibleItemIndex) width * factor else width,
                                    height
                                )
                                .alpha(if (index == visibleItemIndex) factor else 1.0f)

                        )
                    }


                }
            }
        }
    }

}

