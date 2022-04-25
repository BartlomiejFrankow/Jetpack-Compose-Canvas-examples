package com.example.jetpackcomposecanvasexamples.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecanvasexamples.R
import com.example.jetpackcomposecanvasexamples.ui.theme.DirtyWhite
import com.example.jetpackcomposecanvasexamples.utils.NavigationItem

@Composable
fun DrawerItem(item: NavigationItem, selected: Boolean, onItemClick: (NavigationItem) -> Unit) {
    val background = if (selected) R.color.purple_500 else android.R.color.transparent

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(item) }
            .height(48.dp)
            .background(colorResource(id = background))
            .padding(start = 8.dp)
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = stringResource(item.title),
            tint = if (selected) DirtyWhite else MaterialTheme.colors.primaryVariant
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = stringResource(item.title),
            color = if (selected) DirtyWhite else MaterialTheme.colors.primaryVariant
        )
    }
}
