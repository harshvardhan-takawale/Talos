package com.example.harsh.talos.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harsh.talos.Interface.itemClickListener;
import com.example.harsh.talos.MalwareModelActivity;
import com.example.harsh.talos.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class listAdapter extends RecyclerView.Adapter<listAdapter.ItemViewHolder> {

    ArrayList<ApplicationInfo> installedApps;
    Context context;
    PackageManager packageManager;
    ArrayList<String> all_permissions = new ArrayList<>(Arrays.asList("android.permission.ACCESS_ALL_DOWNLOADS", "android.permission.ACCESS_BLUETOOTH_SHARE", "android.permission.ACCESS_CACHE_FILESYSTEM", "android.permission.ACCESS_CHECKIN_PROPERTIES", "android.permission.ACCESS_CONTENT_PROVIDERS_EXTERNALLY", "android.permission.ACCESS_DOWNLOAD_MANAGER", "android.permission.ACCESS_DOWNLOAD_MANAGER_ADVANCED", "android.permission.ACCESS_DRM_CERTIFICATES", "android.permission.ACCESS_EPHEMERAL_APPS", "android.permission.ACCESS_FM_RADIO", "android.permission.ACCESS_INPUT_FLINGER", "android.permission.ACCESS_KEYGUARD_SECURE_STORAGE", "android.permission.ACCESS_LOCATION_EXTRA_COMMANDS", "android.permission.ACCESS_MOCK_LOCATION", "android.permission.ACCESS_MTP", "android.permission.ACCESS_NETWORK_CONDITIONS", "android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_NOTIFICATIONS", "android.permission.ACCESS_NOTIFICATION_POLICY", "android.permission.ACCESS_PDB_STATE", "android.permission.ACCESS_SURFACE_FLINGER", "android.permission.ACCESS_VOICE_INTERACTION_SERVICE", "android.permission.ACCESS_VR_MANAGER", "android.permission.ACCESS_WIFI_STATE", "android.permission.ACCESS_WIMAX_STATE", "android.permission.ACCOUNT_MANAGER", "android.permission.ALLOW_ANY_CODEC_FOR_PLAYBACK", "android.permission.ASEC_ACCESS", "android.permission.ASEC_CREATE", "android.permission.ASEC_DESTROY", "android.permission.ASEC_MOUNT_UNMOUNT", "android.permission.ASEC_RENAME", "android.permission.AUTHENTICATE_ACCOUNTS", "android.permission.BACKUP", "android.permission.BATTERY_STATS", "android.permission.BIND_ACCESSIBILITY_SERVICE", "android.permission.BIND_APPWIDGET", "android.permission.BIND_CARRIER_MESSAGING_SERVICE", "android.permission.BIND_CARRIER_SERVICES", "android.permission.BIND_CHOOSER_TARGET_SERVICE", "android.permission.BIND_CONDITION_PROVIDER_SERVICE", "android.permission.BIND_CONNECTION_SERVICE", "android.permission.BIND_DEVICE_ADMIN", "android.permission.BIND_DIRECTORY_SEARCH", "android.permission.BIND_DREAM_SERVICE", "android.permission.BIND_INCALL_SERVICE", "android.permission.BIND_INPUT_METHOD", "android.permission.BIND_INTENT_FILTER_VERIFIER", "android.permission.BIND_JOB_SERVICE", "android.permission.BIND_KEYGUARD_APPWIDGET", "android.permission.BIND_MIDI_DEVICE_SERVICE", "android.permission.BIND_NFC_SERVICE", "android.permission.BIND_NOTIFICATION_LISTENER_SERVICE", "android.permission.BIND_NOTIFICATION_RANKER_SERVICE", "android.permission.BIND_PACKAGE_VERIFIER", "android.permission.BIND_PRINT_RECOMMENDATION_SERVICE", "android.permission.BIND_PRINT_SERVICE", "android.permission.BIND_PRINT_SPOOLER_SERVICE", "android.permission.BIND_QUICK_SETTINGS_TILE", "android.permission.BIND_REMOTEVIEWS", "android.permission.BIND_REMOTE_DISPLAY", "android.permission.BIND_ROUTE_PROVIDER", "android.permission.BIND_RUNTIME_PERMISSION_PRESENTER_SERVICE", "android.permission.BIND_SCREENING_SERVICE", "android.permission.BIND_TELECOM_CONNECTION_SERVICE", "android.permission.BIND_TEXT_SERVICE", "android.permission.BIND_TRUST_AGENT", "android.permission.BIND_TV_INPUT", "android.permission.BIND_TV_REMOTE_SERVICE", "android.permission.BIND_VOICE_INTERACTION", "android.permission.BIND_VPN_SERVICE", "android.permission.BIND_VR_LISTENER_SERVICE", "android.permission.BIND_WALLPAPER", "android.permission.BLUETOOTH", "android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH_MAP", "android.permission.BLUETOOTH_PRIVILEGED", "android.permission.BLUETOOTH_STACK", "android.permission.BRICK", "android.permission.BROADCAST_CALLLOG_INFO", "android.permission.BROADCAST_NETWORK_PRIVILEGED", "android.permission.BROADCAST_PACKAGE_REMOVED", "android.permission.BROADCAST_PHONE_ACCOUNT_REGISTRATION", "android.permission.BROADCAST_SMS", "android.permission.BROADCAST_STICKY", "android.permission.BROADCAST_WAP_PUSH", "android.permission.CACHE_CONTENT", "android.permission.CALL_PRIVILEGED", "android.permission.CAMERA_DISABLE_TRANSMIT_LED", "android.permission.CAMERA_SEND_SYSTEM_EVENTS", "android.permission.CAPTURE_AUDIO_HOTWORD", "android.permission.CAPTURE_AUDIO_OUTPUT", "android.permission.CAPTURE_SECURE_VIDEO_OUTPUT", "android.permission.CAPTURE_TV_INPUT", "android.permission.CAPTURE_VIDEO_OUTPUT", "android.permission.CARRIER_FILTER_SMS", "android.permission.CHANGE_APP_IDLE_STATE", "android.permission.CHANGE_BACKGROUND_DATA_SETTING", "android.permission.CHANGE_COMPONENT_ENABLED_STATE", "android.permission.CHANGE_CONFIGURATION", "android.permission.CHANGE_DEVICE_IDLE_TEMP_WHITELIST", "android.permission.CHANGE_NETWORK_STATE", "android.permission.CHANGE_WIFI_MULTICAST_STATE", "android.permission.CHANGE_WIFI_STATE", "android.permission.CHANGE_WIMAX_STATE", "android.permission.CLEAR_APP_CACHE", "android.permission.CLEAR_APP_GRANTED_URI_PERMISSIONS", "android.permission.CLEAR_APP_USER_DATA", "android.permission.CONFIGURE_DISPLAY_COLOR_TRANSFORM", "android.permission.CONFIGURE_WIFI_DISPLAY", "android.permission.CONFIRM_FULL_BACKUP", "android.permission.CONNECTIVITY_INTERNAL", "android.permission.CONTROL_INCALL_EXPERIENCE", "android.permission.CONTROL_KEYGUARD", "android.permission.CONTROL_LOCATION_UPDATES", "android.permission.CONTROL_VPN", "android.permission.CONTROL_WIFI_DISPLAY", "android.permission.COPY_PROTECTED_DATA", "android.permission.CREATE_USERS", "android.permission.CRYPT_KEEPER", "android.permission.DELETE_CACHE_FILES", "android.permission.DELETE_PACKAGES", "android.permission.DEVICE_POWER", "android.permission.DIAGNOSTIC", "android.permission.DISABLE_KEYGUARD", "android.permission.DISPATCH_NFC_MESSAGE", "android.permission.DISPATCH_PROVISIONING_MESSAGE", "android.permission.DOWNLOAD_CACHE_NON_PURGEABLE", "android.permission.DUMP", "android.permission.DVB_DEVICE", "android.permission.EXPAND_STATUS_BAR", "android.permission.FACTORY_TEST", "android.permission.FILTER_EVENTS", "android.permission.FLASHLIGHT", "android.permission.FORCE_BACK", "android.permission.FORCE_STOP_PACKAGES", "android.permission.FRAME_STATS", "android.permission.FREEZE_SCREEN", "android.permission.GET_ACCOUNTS_PRIVILEGED", "android.permission.GET_APP_GRANTED_URI_PERMISSIONS", "android.permission.GET_APP_OPS_STATS", "android.permission.GET_DETAILED_TASKS", "android.permission.GET_INTENT_SENDER_INTENT", "android.permission.GET_PACKAGE_IMPORTANCE", "android.permission.GET_PACKAGE_SIZE", "android.permission.GET_PASSWORD", "android.permission.GET_PROCESS_STATE_AND_OOM_SCORE", "android.permission.GET_TASKS", "android.permission.GET_TOP_ACTIVITY_INFO", "android.permission.GLOBAL_SEARCH", "android.permission.GLOBAL_SEARCH_CONTROL", "android.permission.GRANT_RUNTIME_PERMISSIONS", "android.permission.HARDWARE_TEST", "android.permission.HDMI_CEC", "android.permission.INJECT_EVENTS", "android.permission.INSTALL_GRANT_RUNTIME_PERMISSIONS", "android.permission.INSTALL_LOCATION_PROVIDER", "android.permission.INSTALL_PACKAGES", "android.permission.INTENT_FILTER_VERIFICATION_AGENT", "android.permission.INTERACT_ACROSS_USERS", "android.permission.INTERACT_ACROSS_USERS_FULL", "android.permission.INTERNAL_SYSTEM_WINDOW", "android.permission.INTERNET", "android.permission.INVOKE_CARRIER_SETUP", "android.permission.KILL_BACKGROUND_PROCESSES", "android.permission.KILL_UID", "android.permission.LAUNCH_TRUST_AGENT_SETTINGS", "android.permission.LOCAL_MAC_ADDRESS", "android.permission.LOCATION_HARDWARE", "android.permission.LOOP_RADIO", "android.permission.MANAGE_ACCOUNTS", "android.permission.MANAGE_ACTIVITY_STACKS", "android.permission.MANAGE_APP_OPS_RESTRICTIONS", "android.permission.MANAGE_APP_TOKENS", "android.permission.MANAGE_CA_CERTIFICATES", "android.permission.MANAGE_DEVICE_ADMINS", "android.permission.MANAGE_DOCUMENTS", "android.permission.MANAGE_FINGERPRINT", "android.permission.MANAGE_MEDIA_PROJECTION", "android.permission.MANAGE_NETWORK_POLICY", "android.permission.MANAGE_NOTIFICATIONS", "android.permission.MANAGE_PROFILE_AND_DEVICE_OWNERS", "android.permission.MANAGE_SOUND_TRIGGER", "android.permission.MANAGE_USB", "android.permission.MANAGE_USERS", "android.permission.MANAGE_VOICE_KEYPHRASES", "android.permission.MASTER_CLEAR", "android.permission.MEDIA_CONTENT_CONTROL", "android.permission.MODIFY_APPWIDGET_BIND_PERMISSIONS", "android.permission.MODIFY_AUDIO_ROUTING", "android.permission.MODIFY_AUDIO_SETTINGS", "android.permission.MODIFY_CELL_BROADCASTS", "android.permission.MODIFY_DAY_NIGHT_MODE", "android.permission.MODIFY_NETWORK_ACCOUNTING", "android.permission.MODIFY_PARENTAL_CONTROLS", "android.permission.MODIFY_PHONE_STATE", "android.permission.MOUNT_FORMAT_FILESYSTEMS", "android.permission.MOUNT_UNMOUNT_FILESYSTEMS", "android.permission.MOVE_PACKAGE", "android.permission.NET_ADMIN", "android.permission.NET_TUNNELING", "android.permission.NFC", "android.permission.NFC_HANDOVER_STATUS", "android.permission.NOTIFY_PENDING_SYSTEM_UPDATE", "android.permission.OBSERVE_GRANT_REVOKE_PERMISSIONS", "android.permission.OEM_UNLOCK_STATE", "android.permission.OVERRIDE_WIFI_CONFIG", "android.permission.PACKAGE_USAGE_STATS", "android.permission.PACKAGE_VERIFICATION_AGENT", "android.permission.PACKET_KEEPALIVE_OFFLOAD", "android.permission.PEERS_MAC_ADDRESS", "android.permission.PERFORM_CDMA_PROVISIONING", "android.permission.PERFORM_SIM_ACTIVATION", "android.permission.PERSISTENT_ACTIVITY", "android.permission.PROCESS_CALLLOG_INFO", "android.permission.PROCESS_PHONE_ACCOUNT_REGISTRATION", "android.permission.PROVIDE_TRUST_AGENT", "android.permission.QUERY_DO_NOT_ASK_CREDENTIALS_ON_BOOT", "android.permission.READ_BLOCKED_NUMBERS", "android.permission.READ_DREAM_STATE", "android.permission.READ_FRAME_BUFFER", "android.permission.READ_INPUT_STATE", "android.permission.READ_INSTALL_SESSIONS", "android.permission.READ_LOGS", "android.permission.READ_NETWORK_USAGE_HISTORY", "android.permission.READ_OEM_UNLOCK_STATE", "android.permission.READ_PRECISE_PHONE_STATE", "android.permission.READ_PRIVILEGED_PHONE_STATE", "android.permission.READ_PROFILE", "android.permission.READ_SEARCH_INDEXABLES", "android.permission.READ_SOCIAL_STREAM", "android.permission.READ_SYNC_SETTINGS", "android.permission.READ_SYNC_STATS", "android.permission.READ_USER_DICTIONARY", "android.permission.READ_WIFI_CREDENTIAL", "android.permission.REAL_GET_TASKS", "android.permission.REBOOT", "android.permission.RECEIVE_BLUETOOTH_MAP", "android.permission.RECEIVE_BOOT_COMPLETED", "android.permission.RECEIVE_DATA_ACTIVITY_CHANGE", "android.permission.RECEIVE_EMERGENCY_BROADCAST", "android.permission.RECEIVE_MEDIA_RESOURCE_USAGE", "android.permission.RECEIVE_STK_COMMANDS", "android.permission.RECEIVE_WIFI_CREDENTIAL_CHANGE", "android.permission.RECOVERY", "android.permission.REGISTER_CALL_PROVIDER", "android.permission.REGISTER_CONNECTION_MANAGER", "android.permission.REGISTER_SIM_SUBSCRIPTION", "android.permission.REGISTER_WINDOW_MANAGER_LISTENERS", "android.permission.REMOTE_AUDIO_PLAYBACK", "android.permission.REMOVE_DRM_CERTIFICATES", "android.permission.REMOVE_TASKS", "android.permission.REORDER_TASKS", "android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS", "android.permission.REQUEST_INSTALL_PACKAGES", "android.permission.RESET_FINGERPRINT_LOCKOUT", "android.permission.RESET_SHORTCUT_MANAGER_THROTTLING", "android.permission.RESTART_PACKAGES", "android.permission.RETRIEVE_WINDOW_CONTENT", "android.permission.RETRIEVE_WINDOW_TOKEN", "android.permission.REVOKE_RUNTIME_PERMISSIONS", "android.permission.SCORE_NETWORKS", "android.permission.SEND_CALL_LOG_CHANGE", "android.permission.SEND_DOWNLOAD_COMPLETED_INTENTS", "android.permission.SEND_RESPOND_VIA_MESSAGE", "android.permission.SEND_SMS_NO_CONFIRMATION", "android.permission.SERIAL_PORT", "android.permission.SET_ACTIVITY_WATCHER", "android.permission.SET_ALWAYS_FINISH", "android.permission.SET_ANIMATION_SCALE", "android.permission.SET_DEBUG_APP", "android.permission.SET_INPUT_CALIBRATION", "android.permission.SET_KEYBOARD_LAYOUT", "android.permission.SET_ORIENTATION", "android.permission.SET_POINTER_SPEED", "android.permission.SET_PREFERRED_APPLICATIONS", "android.permission.SET_PROCESS_LIMIT", "android.permission.SET_SCREEN_COMPATIBILITY", "android.permission.SET_TIME", "android.permission.SET_TIME_ZONE", "android.permission.SET_WALLPAPER", "android.permission.SET_WALLPAPER_COMPONENT", "android.permission.SET_WALLPAPER_HINTS", "android.permission.SHUTDOWN", "android.permission.SIGNAL_PERSISTENT_PROCESSES", "android.permission.START_ANY_ACTIVITY", "android.permission.START_PRINT_SERVICE_CONFIG_ACTIVITY", "android.permission.START_TASKS_FROM_RECENTS", "android.permission.STATUS_BAR", "android.permission.STATUS_BAR_SERVICE", "android.permission.STOP_APP_SWITCHES", "android.permission.STORAGE_INTERNAL", "android.permission.SUBSCRIBED_FEEDS_READ", "android.permission.SUBSCRIBED_FEEDS_WRITE", "android.permission.SUBSTITUTE_NOTIFICATION_APP_NAME", "android.permission.SYSTEM_ALERT_WINDOW", "android.permission.TABLET_MODE", "android.permission.TEMPORARY_ENABLE_ACCESSIBILITY", "android.permission.TETHER_PRIVILEGED", "android.permission.TRANSMIT_IR", "android.permission.TRUST_LISTENER", "android.permission.TV_INPUT_HARDWARE", "android.permission.TV_VIRTUAL_REMOTE_CONTROLLER", "android.permission.UPDATE_APP_OPS_STATS", "android.permission.UPDATE_CONFIG", "android.permission.UPDATE_DEVICE_STATS", "android.permission.UPDATE_LOCK", "android.permission.UPDATE_LOCK_TASK_PACKAGES", "android.permission.USER_ACTIVITY", "android.permission.USE_CREDENTIALS", "android.permission.VIBRATE", "android.permission.WAKE_LOCK", "android.permission.WRITE_APN_SETTINGS", "android.permission.WRITE_BLOCKED_NUMBERS", "android.permission.WRITE_DREAM_STATE", "android.permission.WRITE_GSERVICES", "android.permission.WRITE_MEDIA_STORAGE", "android.permission.WRITE_PROFILE", "android.permission.WRITE_SECURE_SETTINGS", "android.permission.WRITE_SETTINGS", "android.permission.WRITE_SMS", "android.permission.WRITE_SOCIAL_STREAM", "android.permission.WRITE_SYNC_SETTINGS", "android.permission.WRITE_USER_DICTIONARY"));

    public listAdapter(ArrayList<ApplicationInfo> installedApps, Context context, PackageManager packageManager) {
        this.installedApps = installedApps;
        this.context = context;
        this.packageManager = packageManager;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, final int i) {

        itemViewHolder.mTitle.setText(installedApps.get(i).loadLabel(packageManager).toString());
        itemViewHolder.mLogo.setImageDrawable(installedApps.get(i).loadIcon(packageManager));
        itemViewHolder.setonItemClickListener(new itemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                try {
                    PackageInfo packageInfo = packageManager.getPackageInfo(installedApps.get(i).packageName,   PackageManager.GET_PERMISSIONS);
                    ArrayList<String> app_perms_requested = new ArrayList<>(Arrays.asList(packageInfo                                                                                                               .requestedPermissions));

                    float[] arr = new float[324];
                    if (app_perms_requested.isEmpty()){
                        Arrays.fill(arr, 0);
                    }else {
                        Arrays.fill(arr, 0);
                        Set hashset = new HashSet(app_perms_requested);
                        for(int i = 0; i < all_permissions.size(); i++)
                        {
                            if(hashset.contains(all_permissions.get(i)))
                            {
                                arr[i] = 1;

                            }
                        }
                    }

                    Intent intent = new Intent(context, MalwareModelActivity.class);
                    intent.putExtra("Malware_array", arr);
                    context.startActivity(intent);

                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return installedApps.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView mTitle;
        public itemClickListener mlistener = null;
        public ImageView mLogo;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.textView3);
            mLogo = (ImageView) itemView.findViewById(R.id.imgView);
            itemView.setOnClickListener(this);
        }

        void setonItemClickListener(itemClickListener listener){
            this.mlistener = listener;
        }


        @Override
        public void onClick(View view) {
            this.mlistener.onItemClickListener(view,getAdapterPosition());

        }
    }
}